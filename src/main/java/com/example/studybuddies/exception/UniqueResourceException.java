package com.example.studybuddies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UniqueResourceException extends RuntimeException {

	private static final long serialVersionUID = -6406928791499131179L;

	public UniqueResourceException(String msg) {
		super(msg);
	}

}
