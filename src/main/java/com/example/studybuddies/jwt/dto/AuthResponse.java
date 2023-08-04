package com.example.studybuddies.jwt.dto;

import java.util.HashMap;
import java.util.Map;

import com.example.studybuddies.jwt.impl.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {

	private int studentId;

	private int tutorId;

	private String email;

	private String accessToken;

	private String refreshToken;

	private String role;

	public AuthResponse(int studentId, int tutorId, String email, String role, String accessToken,
			String refreshToken) {
		this.studentId = studentId;
		this.tutorId = tutorId;
		this.email = email;
		this.role = role;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public static AuthResponse build(UserDetailsImpl userDetail, String accessToken, String refreshToken) {

		final AuthResponse authResponse = new AuthResponse(userDetail.getStudentId(), userDetail.getTutorId(),
				userDetail.getEmail(), userDetail.getRole(), accessToken, refreshToken);
		return authResponse;
	}
}
