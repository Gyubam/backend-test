package com.example.backendtest.controller;

import com.example.backendtest.provider.JwtTokenProvider;
import com.example.backendtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "signUp";
    }

    @GetMapping("/test")
    public String test(Principal principal, Model model) {
        System.out.println(principal.getName());

        return "test";
    }

    @GetMapping("/user/v1")
    public String user() {
        return "test";
    }


}
