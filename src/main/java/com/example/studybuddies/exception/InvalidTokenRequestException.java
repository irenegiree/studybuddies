package com.example.studybuddies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidTokenRequestException extends RuntimeException {

	private static final long serialVersionUID = 331538677939788300L;

	public InvalidTokenRequestException(String message) {
		super(message);
	}
}
