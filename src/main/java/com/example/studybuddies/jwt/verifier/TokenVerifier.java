package com.example.studybuddies.jwt.verifier;

import org.springframework.stereotype.Component;

@Component
public class TokenVerifier {

	public boolean verify(String jti) {
		// TODO: check jti in redis cache or db
		return true;
	}

}
