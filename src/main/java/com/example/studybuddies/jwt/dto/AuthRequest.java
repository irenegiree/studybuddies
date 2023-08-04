package com.example.studybuddies.jwt.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequest {
	
	@NotBlank(message = "email can't be null or blank.")
	@Email(message = "email is not valid.")
	private String email;

	@NotBlank(message = "password can't be null or blank.")
	private String password;
}
