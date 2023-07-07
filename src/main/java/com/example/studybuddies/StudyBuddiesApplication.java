package com.example.studybuddies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class, SecurityAutoConfiguration.class})
public class StudyBuddiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyBuddiesApplication.class, args);
	}

}
