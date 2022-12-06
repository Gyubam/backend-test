package com.example.backendtest.service;

import com.example.backendtest.dto.Token;
import com.example.backendtest.dto.UserJoinDto;
import com.example.backendtest.dto.UserLoginDto;
import com.example.backendtest.entity.RefreshToken;
import com.example.backendtest.entity.User;
import com.example.backendtest.provider.JwtTokenProvider;
import com.example.backendtest.repository.RefreshTokenRepository;
import com.example.backendtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveUser(UserJoinDto dto) {
        User user = new User(dto.getEmail(),
                dto.getPassword(),
                dto.getUsername(),
                dto.getTel(),
                dto.getRoles());

        userRepository.save(user);
    }

    public Token login(UserLoginDto dto) {

        // 이메일 및 비밀번호 유효성 체크
        User findUser = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }


        Token tokenDto = jwtTokenProvider.createToken(findUser.getUsername(), findUser.getRoles());
        RefreshToken refreshToken = RefreshToken.builder().keyEmail(tokenDto.getKey()).refreshToken(tokenDto.getRefreshToken()).build();
        String loginUserEmail = refreshToken.getKeyEmail();

        if(refreshTokenRepository.existsByKeyEmail(loginUserEmail)){
            log.info("기존의 존재하는 refresh 토큰 삭제");
            refreshTokenRepository.deleteByKeyEmail(loginUserEmail);
        }
        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    public Optional<User> findByEmail(String email) {
        Optional<User> findUser = userRepository.findByEmail(email);
        if (findUser.isPresent()) {
            return findUser;
        }
        return null;
    }
}
