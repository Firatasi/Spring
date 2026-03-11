package com.firat.example.security;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createRefreshToken(String username) {
        revokeAllUserTokens(username);

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .username(username)
                .expiryDate(LocalDateTime.now().plusDays(7))
                .used(false)
                .revoked(false)
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> new RuntimeException("Refresh token bulunamadı !"));

        if (refreshToken.isRevoked()){
            throw new RuntimeException("Refresh token iptal edilmiş");
        }

        if (refreshToken.isUsed()) {
            throw new RuntimeException("Refresh token daha önce kullanılmış");
        }

        if (refreshToken.isExpired()) {
            throw new RuntimeException("Refresh token süresi dolmuş");
        }

        return refreshToken;

    }

    public void markAsUsed(RefreshToken refreshToken) {
        refreshToken.setUsed(true);
        refreshTokenRepository.save(refreshToken);
    }

    public void revokeAllUserTokens(String username) {
        List<RefreshToken> tokens = refreshTokenRepository.findAllByUsernameAndRevokedFalse(username);
        for (RefreshToken token : tokens) {
            token.setRevoked(true);
        }
        refreshTokenRepository.saveAll(tokens);
    }



}
