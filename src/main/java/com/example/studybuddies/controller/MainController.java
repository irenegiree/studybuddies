package com.example.studybuddies.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
