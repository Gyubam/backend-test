package com.example.backendtest.service;

import com.example.backendtest.dto.UserJoinDto;
import com.example.backendtest.dto.UserLoginDto;
import com.example.backendtest.entity.User;
import com.example.backendtest.provider.JwtTokenProvider;
import com.example.backendtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void saveUser(UserJoinDto dto) {
        User user = new User(dto.getEmail(),
                dto.getPassword(),
                dto.getUsername(),
                dto.getTel(),
                dto.getRoles());

        userRepository.save(user);
    }

    public String login(UserLoginDto dto) {

        User findUser = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(findUser.getUsername(), findUser.getRoles());
    }

    public Optional<User> findByEmail(String email) {
        Optional<User> findUser = userRepository.findByEmail(email);
        if (findUser.isPresent()) {
            return findUser;
        }
        return null;
    }
}
