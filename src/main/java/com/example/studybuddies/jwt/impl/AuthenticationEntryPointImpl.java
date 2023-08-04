package com.example.studybuddies.jwt.impl;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	@Qualifier("handlerExceptionResolver")
	@Autowired
	private HandlerExceptionResolver resolver;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {

		if (request.getAttribute("javax.servlet.error.exception") != null) {
			Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
			resolver.resolveException(request, response, null, (Exception) throwable);
			return;
		}

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
	}
}
