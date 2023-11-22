package com.example.movienewsfeed.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    @GetMapping()
    public String profile() {
        return "profile";
    }

    @GetMapping("/settings")
    public String modifyProfile() {
        return "/settings/profile-introduction";
    }

    @GetMapping("/password")
    public String modifyPassword() {
        return "/settings/password";
    }
}
