package com.example.studybuddies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserLoginException extends RuntimeException {

	private static final long serialVersionUID = 7786007244278948251L;

	public UserLoginException(String message) {
		super(message);
	}
}
