package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.domain.User;
import com.gsilverio.simpleapi.domain.dto.auth.request.LoginRequest;
import com.gsilverio.simpleapi.domain.dto.auth.request.RefreshTokenRequest;
import com.gsilverio.simpleapi.domain.dto.auth.response.AuthResponse;
import com.gsilverio.simpleapi.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    public AuthResponse login(LoginRequest request){
        User user = userService.findUserByEmail(request.email());

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid credentials");
        }

        String accessToken = tokenService.generateToken(user.getEmail());
        String refreshToken = tokenService.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshToken(RefreshTokenRequest request){
        String token = request.refreshToken();

        if (!tokenService.isTokenValid(token))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid or expired refresh token");

        String email = tokenService.extractEmail(token);

        String newAccessToken = tokenService.generateToken(email);
        String newRefreshToken = tokenService.generateRefreshToken(email);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }
}
