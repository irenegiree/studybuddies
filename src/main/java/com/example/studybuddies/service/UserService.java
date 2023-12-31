package com.example.studybuddies.service;

import com.example.studybuddies.dto.UserRegistrationDto;
import com.example.studybuddies.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto user);
    public void createAdminUser();
}
