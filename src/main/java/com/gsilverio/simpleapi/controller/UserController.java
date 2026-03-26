package com.gsilverio.simpleapi.controller;

import com.gsilverio.simpleapi.model.Loan;
import com.gsilverio.simpleapi.model.dto.request.user.LoanBookUserRequest;
import com.gsilverio.simpleapi.model.dto.request.user.UserRequest;
import com.gsilverio.simpleapi.model.dto.response.config.ApiResponse;
import com.gsilverio.simpleapi.model.dto.response.user.UserResponse;
import com.gsilverio.simpleapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@Tag(name = "user")
public class UserController {
    @Autowired
    private UserService service;

    @Operation(summary = "list all users", description = "return a list of all the users")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> listAll() {
        var users = service.listAll();
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @Operation(summary = "create a new user", description = "insert a new record of a user in the system")
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody UserRequest request){
        var createdUser = service.save(request);
        return ResponseEntity.ok(ApiResponse.success(createdUser));
    }

    @Operation(summary = "loan a book", description = "create a record of a loan in the system")
    @PostMapping("loan-book")
    public ResponseEntity<ApiResponse<Loan>> loanBook(@Valid @RequestBody LoanBookUserRequest request){
        var createdLoan = service.loanBook(request);
        return ResponseEntity.ok(ApiResponse.success(createdLoan));
    }
}
