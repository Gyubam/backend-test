package com.example.backendtest.repository;

import com.example.backendtest.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    boolean existsByKeyEmail(String userEmail);

    void deleteByKeyEmail(String userEmail);
}
