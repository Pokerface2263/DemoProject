package com.solvatech.onboarding.demoproject.repository;

import com.solvatech.onboarding.demoproject.model.RefreshToken;
import com.solvatech.onboarding.demoproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByUsername(String username);
}
