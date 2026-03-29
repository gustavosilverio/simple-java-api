package com.gsilverio.simpleapi.controller;

import com.gsilverio.simpleapi.domain.dto.loan.request.CreateLoanRequest;
import com.gsilverio.simpleapi.domain.dto.loan.response.CreateLoanResponse;
import com.gsilverio.simpleapi.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("loan")
@RequiredArgsConstructor
@Tag(name = "loan")
public class LoanController {
    private final LoanService service;

    @Operation(summary = "create a loan", description = "insert a new record of a loan in the library")
    @PostMapping
    public CreateLoanResponse create(@Valid @RequestBody CreateLoanRequest request){
        return service.save(request);
    }
}
