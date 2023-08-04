package com.example.studybuddies.jwt.util;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.crypto.SecretKey;

import com.example.studybuddies.exception.InvalidTokenRequestException;
import com.example.studybuddies.jwt.exception.InvalidJwtToken;
import com.example.studybuddies.jwt.impl.UserDetailsImpl;
import com.example.studybuddies.jwt.impl.UserDetailsServiceImpl;
import com.example.studybuddies.jwt.verifier.TokenVerifier;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.example.studybuddies.jwt.config.JwtConfig;
import com.example.studybuddies.exception.UserLoginException;


@Component
public class JwtUtils {

	private final String REFRESH_TOKEN_ROLE = "ROLE_REFRESH_TOKEN";
	private final String RR_LOGIN_REQUIRED = "Re_Login_Required";
	private final String TOKEN_REFRESH_REQUIRED = "Token_Refresh_Required";

	@Value("${studybuddies.app.jwtSecret}")
	private String jwtSecret;

	@Value("${studybuddies.app.accessTokenExpirationHour}")
	private long accessTokenExpirationHour;

	@Value("${studybuddies.app.refreshTokenExpirationDay}")
	private int refreshTokenExpirationDay;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private TokenVerifier tokenVerifier;

	public String generateAccessToken(Authentication authentication) {

		final SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		final UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		final Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());
		claims.put("role",
				userPrincipal.getRole());
		claims.setIssuedAt(new Date(System.currentTimeMillis()));
		claims.setExpiration(
				Date.from(LocalDateTime.now().plusHours(accessTokenExpirationHour).atZone(ZoneId.systemDefault()).toInstant()));

		return Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS256).compact();
	}

	public String generateRefreshToken(Authentication authentication) {

		final SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		final UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		final Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());
		claims.put("role", REFRESH_TOKEN_ROLE);
		claims.setId(UUID.randomUUID().toString());
		claims.setIssuedAt(new Date(System.currentTimeMillis()));
		claims.setExpiration(
				Date.from(LocalDateTime.now().plusDays(refreshTokenExpirationDay).atZone(ZoneId.systemDefault()).toInstant()));

		return Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS256).compact();
	}

	public void processTokenWithRequest(HttpServletRequest request) {
		final String accessToken = parseToken(request);
		processToken(accessToken, false, request);
	}

	public void processTokenWithRefreshToken(String refreshToken, HttpServletRequest request) {
		processToken(refreshToken, true, request);
	}

	private void processToken(String jwt, boolean isRefreshToken, HttpServletRequest request) {

		if (jwt != null && validateJwtToken(jwt, isRefreshToken)) {
			final Jws<Claims> jwsClaims = retrieveClaimsFromToken(jwt);

			final String email = jwsClaims.getBody().getSubject();
			final String role = jwsClaims.getBody().get("role", String.class);

			if (isRefreshToken) {
				final String jti = jwsClaims.getBody().getId();

				if (!tokenVerifier.verify(jti)) {
					throw new InvalidJwtToken();
				}
			}

			final List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(role));

			UserDetails userDetails = null;
			try {
				userDetails = userDetailsService.loadUserByUsername(email);
			} catch (UsernameNotFoundException e) {
				throw new UserLoginException(e.getMessage());
			}

			final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, authorities);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private String parseToken(HttpServletRequest request) {
		final boolean isPermittedURL = checkUriInRequest(request,
				Arrays.asList(JwtConfig.LOGIN_URL, JwtConfig.REFRESH_TOKEN_URL));

		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ") && !isPermittedURL) {
			return headerAuth.replace("Bearer ", "");
		}

		return null;
	}

	private Jws<Claims> retrieveClaimsFromToken(String token) {
		final SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
	}

	private boolean validateJwtToken(String authToken, boolean isRefreshTokenURL) {
		final String msg = isRefreshTokenURL ? RR_LOGIN_REQUIRED : TOKEN_REFRESH_REQUIRED;

		try {
			SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			throw new InvalidTokenRequestException("Incorrect JWT signature");
		} catch (MalformedJwtException ex) {
			throw new InvalidTokenRequestException("Malformed JWT token");
		} catch (ExpiredJwtException ex) {
			throw new InvalidTokenRequestException(msg);
		} catch (UnsupportedJwtException ex) {
			throw new InvalidTokenRequestException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new InvalidTokenRequestException("Illegal argument token");
		}
	}

	private boolean checkUriInRequest(HttpServletRequest request, List<String> permittedURLs) {
		final String currentURI = request.getRequestURI().trim();
		return permittedURLs.contains(currentURI);
	}
}
