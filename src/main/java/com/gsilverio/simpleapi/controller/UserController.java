package com.gsilverio.simpleapi.controller;

import com.gsilverio.simpleapi.domain.dto.user.request.UserRequest;
import com.gsilverio.simpleapi.domain.dto.config.ApiResponse;
import com.gsilverio.simpleapi.domain.dto.user.response.UserResponse;
import com.gsilverio.simpleapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@Tag(name = "user")
public class UserController {
    private final UserService service;

    @Operation(summary = "list all users", description = "return a list of all the users")
    @GetMapping
    public ApiResponse<List<UserResponse>> listAll() {
        var users = service.listAll();
        return ApiResponse.success(users);
    }

    @Operation(summary = "create a new user", description = "insert a new record of a user in the system")
    @PostMapping
    public ApiResponse<UserResponse> create(@Valid @RequestBody UserRequest request){
        var createdUser = service.save(request);
        return ApiResponse.success(createdUser);
    }
}
