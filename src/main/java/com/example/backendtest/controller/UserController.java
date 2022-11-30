package com.example.backendtest.controller;


import com.example.backendtest.dto.UserJoinDto;
import com.example.backendtest.dto.UserLoginDto;
import com.example.backendtest.entity.User;
import com.example.backendtest.provider.JwtTokenProvider;
import com.example.backendtest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    // 회원가입
    @PostMapping("/join")
    public String join(@RequestBody Map<String, String> user) {

        UserJoinDto dto = new UserJoinDto(user.get("email"),
                passwordEncoder.encode(user.get("password")),
                user.get("username"),
                user.get("tel"),
                Collections.singletonList("ROLE_USER"));

        userService.saveUser(dto);

        return dto.getEmail();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {

        UserLoginDto dto = new UserLoginDto(user.get("email"),
                user.get("password"));

        String token = userService.login(dto);

        return token;

    }
}
