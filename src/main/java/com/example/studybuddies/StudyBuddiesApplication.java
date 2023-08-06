package com.example.studybuddies;

import com.example.studybuddies.service.UserService;
import com.example.studybuddies.service.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class, SecurityAutoConfiguration.class})
public class StudyBuddiesApplication {

	@Autowired
	UserService userService;

	@PostConstruct
	public void insertAdmin() throws Exception {
		userService.createAdminUser();
	}

	public static void main(String[] args) {
		SpringApplication.run(StudyBuddiesApplication.class, args);
	}

}
