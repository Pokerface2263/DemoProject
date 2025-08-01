package com.solvatech.onboarding.demoproject.service;

import com.solvatech.onboarding.demoproject.model.RefreshToken;
import com.solvatech.onboarding.demoproject.model.User;
import com.solvatech.onboarding.demoproject.repository.RefreshTokenRepository;
import com.solvatech.onboarding.demoproject.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JWTUtil jwtUtil;


    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,  JWTUtil jwtUtil) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtUtil = jwtUtil;
    }

    public void saveRefreshToken(String refreshToken, String username){
        RefreshToken newRefreshToken = new RefreshToken();
        newRefreshToken.setToken(refreshToken);
        newRefreshToken.setUsername(username);
        newRefreshToken.setExpiresAt(jwtUtil.extractExpiration(refreshToken).toInstant());
        refreshTokenRepository.save(newRefreshToken);
    }

    public Optional<RefreshToken> getRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken);
    }

    public void deleteRefreshToken(String username) {
        refreshTokenRepository.deleteByUsername(username);
    }
}
