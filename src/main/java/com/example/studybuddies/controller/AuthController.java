package com.example.studybuddies.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.example.studybuddies.dto.UserRegistrationDto;
import com.example.studybuddies.jwt.dto.AuthRequest;
import com.example.studybuddies.jwt.dto.AuthResponse;
import com.example.studybuddies.jwt.impl.UserDetailsImpl;
import com.example.studybuddies.jwt.util.JwtUtils;
import com.example.studybuddies.misc.TrackExecutionTime;
import com.example.studybuddies.model.CurrentLoggedInUser;
import com.example.studybuddies.model.Student;
import com.example.studybuddies.model.User;
import com.example.studybuddies.repository.CurrentLoggedInUserRepository;
import com.example.studybuddies.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;


@Controller
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CurrentLoggedInUserRepository cluRepo;

	@GetMapping("/login")
	public String login(Model model) {
		if(cluRepo.findAll().size()>0){
			CurrentLoggedInUser clu = cluRepo.findAll().get(0);
			if(clu.getRole().equals("ROLE_STUDENT")) {
				return "redirect:/student-profile";
			}
			else if(clu.getRole().equals("ROLE_TUTOR")){
				return "redirect:/tutor-profile";
			} else {
				return "redirect:/admin/students";
			}

		}
		else {
			model.addAttribute("user", new UserRegistrationDto());
			return "login";
		}
	}

	@PostMapping("/login")
	public String authenticateUser(@Valid AuthRequest user, BindingResult result) {


		final Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getEmail(), user
						.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);


		final String accessToken = jwtUtils.generateAccessToken(authentication);
		final String refreshToken = jwtUtils.generateRefreshToken(authentication);

		User foundUser = userRepository.findByEmail(user.getEmail());
		final List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(foundUser.getRole()));
		final UserDetailsImpl userDetail = new UserDetailsImpl(foundUser.getStudentId(), foundUser.getTutorId(), foundUser.getEmail(), foundUser.getPassword(), foundUser.getRole(), authorities);
		final AuthResponse authResponse = AuthResponse.build(userDetail, accessToken, refreshToken);
		cluRepo.deleteAll();
		CurrentLoggedInUser clu = new CurrentLoggedInUser(user.getEmail(), foundUser.getRole());
		cluRepo.save(clu);
			if(clu.getRole().equals("ROLE_STUDENT")) {
				return "redirect:/student-profile";
			}
			else if(clu.getRole().equals("ROLE_TUTOR")){
				return "redirect:/tutor-profile";
			} else {
				return "redirect:/admin/students";
			}

	}

	@GetMapping("/userlogout")
	public String logout() {
			System.out.println("**********************************************************Entered Logout function.");
			cluRepo.deleteAll();
			return "redirect:/login";
	}

	@PostMapping("refresh-token")
	@TrackExecutionTime
	public ResponseEntity<String> refreshToken(@RequestBody Map<String, String> refreshTokenObj,
			HttpServletRequest request) {

		jwtUtils.processTokenWithRefreshToken(refreshTokenObj.get("refreshToken"), request);

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final String renewedAccessToken = jwtUtils.generateAccessToken(authentication);

		return new ResponseEntity<String>(renewedAccessToken, HttpStatus.OK);
	}


}
