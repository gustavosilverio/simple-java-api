package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.model.User;
import com.gsilverio.simpleapi.model.dto.request.LoginRequest;
import com.gsilverio.simpleapi.model.dto.request.RefreshTokenRequest;
import com.gsilverio.simpleapi.model.dto.response.AuthResponse;
import com.gsilverio.simpleapi.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public AuthResponse login(LoginRequest request){
        User user = userService.findByEmail(request.email());

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid credentials");
        }

        String accessToken = tokenService.generateToken(user.getEmail());
        String refreshToken = tokenService.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }

    @PostMapping("refresh-token")
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request){
        String token = request.refreshToken();

        if (!tokenService.isTokenValid(token))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid or expired refresh token");

        String email = tokenService.extractEmail(token);

        String newAccessToken = tokenService.generateToken(email);
        String newRefreshToken = tokenService.generateRefreshToken(email);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }
}
