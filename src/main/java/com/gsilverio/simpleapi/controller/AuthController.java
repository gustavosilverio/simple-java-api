package com.gsilverio.simpleapi.controller;

import com.gsilverio.simpleapi.model.dto.request.LoginRequest;
import com.gsilverio.simpleapi.model.dto.request.RefreshTokenRequest;
import com.gsilverio.simpleapi.model.dto.response.AuthResponse;
import com.gsilverio.simpleapi.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("auth")
@Tag(name = "auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return service.login(request);
    }

    @PostMapping("refresh-token")
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request){
        return service.refreshToken(request);
    }
}
