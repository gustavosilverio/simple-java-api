package com.gsilverio.simpleapi.controller;

import com.gsilverio.simpleapi.domain.dto.auth.request.LoginRequest;
import com.gsilverio.simpleapi.domain.dto.auth.request.RefreshTokenRequest;
import com.gsilverio.simpleapi.domain.dto.auth.response.AuthResponse;
import com.gsilverio.simpleapi.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
@Tag(name = "auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request){
        return service.login(request);
    }

    @PostMapping("refresh-token")
    public AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request){
        return service.refreshToken(request);
    }
}
