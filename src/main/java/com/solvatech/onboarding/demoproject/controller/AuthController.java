package com.solvatech.onboarding.demoproject.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.solvatech.onboarding.demoproject.dto.AuthResponse;
import com.solvatech.onboarding.demoproject.dto.RefreshRequest;
import com.solvatech.onboarding.demoproject.dto.UserAuthDTO;
import com.solvatech.onboarding.demoproject.dto.UserDTO;
import com.solvatech.onboarding.demoproject.model.RefreshToken;
import com.solvatech.onboarding.demoproject.model.User;
import com.solvatech.onboarding.demoproject.security.JWTUtil;
import com.solvatech.onboarding.demoproject.service.RefreshTokenService;
import com.solvatech.onboarding.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public  AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserService userService,  RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody UserAuthDTO user){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            String accessToken = jwtUtil.generateAccessToken(user.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
            refreshTokenService.deleteRefreshToken(user.getEmail());
            refreshTokenService.saveRefreshToken(refreshToken, user.getEmail());

            return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        User user =  userService.createUser(userDTO);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        try {
            String username = jwtUtil.validateRefreshTokenAndRetriveClaim(refreshToken);

            Optional<RefreshToken> refreshTokenFromDB = refreshTokenService.getRefreshToken(refreshToken);

            if (refreshTokenFromDB.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token not found");
            }

            if(refreshTokenFromDB.get().getExpiresAt().isBefore(Instant.now())) {
                refreshTokenService.deleteRefreshToken(username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired");
            }

            String newAccessToken = jwtUtil.generateAccessToken(username);
            return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }
}
