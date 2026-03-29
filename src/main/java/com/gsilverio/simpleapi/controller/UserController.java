package com.gsilverio.simpleapi.controller;

import com.gsilverio.simpleapi.domain.dto.user.request.UserRequest;
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
    public List<UserResponse> listAll() {
        return service.listAll();
    }

    @Operation(summary = "create a new user", description = "insert a new record of a user in the system")
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest request){
        return service.save(request);
    }
}
