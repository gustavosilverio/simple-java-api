package com.gsilverio.simpleapi.controller;

import com.gsilverio.simpleapi.model.dto.request.LoginRequest;
import com.gsilverio.simpleapi.model.dto.request.RefreshTokenRequest;
import com.gsilverio.simpleapi.model.dto.response.ApiResponse;
import com.gsilverio.simpleapi.model.dto.response.AuthResponse;
import com.gsilverio.simpleapi.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request){
        var response = service.login(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("refresh-token")
    public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest request){
        var response = service.refreshToken(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
