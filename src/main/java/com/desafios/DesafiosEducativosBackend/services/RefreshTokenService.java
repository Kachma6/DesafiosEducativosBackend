package com.desafios.DesafiosEducativosBackend.services;

import com.desafios.DesafiosEducativosBackend.domain.entities.RefreshToken;
import com.desafios.DesafiosEducativosBackend.repositories.RefreshTokenRepository;
import com.desafios.DesafiosEducativosBackend.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
//    @Value("${bezkoder.app.jwtRefreshExpirationMs}")
//    private Long refreshTokenDurationMs;


    private final RefreshTokenRepository refreshTokenRepository;


    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {

        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Integer userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis( 3600 * 1000));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new IllegalArgumentException("El token a expirado");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Integer userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
