package com.example.backendtest.controller;

import com.example.backendtest.entity.User;
import com.example.backendtest.provider.JwtTokenProvider;
import com.example.backendtest.repository.UserRepository;
import com.example.backendtest.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final EmailService emailService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "signUp";
    }

    @GetMapping("/test")
    public String test(Principal principal, @AuthenticationPrincipal User user, Model model) {
        System.out.println(principal.getName());
        System.out.println("user.getEmail() = " + user.getEmail());

        return "test";
    }

    @GetMapping("/test2")
    public String test2(Principal principal, @AuthenticationPrincipal User user, Model model) {
        System.out.println(principal.getName());
        System.out.println("user.getEmail() = " + user.getEmail());
        System.out.println("user.getPassword() = " + user.getPassword());
        System.out.println("user.getTel() = " + user.getTel());
        System.out.println("user.getRoles() = " + user.getRoles());
        System.out.println("user.getAuthorities() = " + user.getAuthorities());

        return "test2";
    }

    @GetMapping("/user/v1")
    public String user() {
        return "test";
    }

    @PostMapping("/emailConfirm")
    public String emailConfirm(@RequestParam String email) throws Exception {

        String confirm = emailService.sendSimpleMessage(email);

        return confirm;
    }


}
