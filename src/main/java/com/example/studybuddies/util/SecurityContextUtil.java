package com.example.studybuddies.util;

import com.example.studybuddies.jwt.impl.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityContextUtil {

    public static UserDetailsImpl retrieveLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return (UserDetailsImpl) authentication.getPrincipal();
    }
}