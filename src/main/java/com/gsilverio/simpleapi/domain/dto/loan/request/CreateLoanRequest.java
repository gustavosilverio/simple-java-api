package com.gsilverio.simpleapi.domain.dto.loan.request;

import jakarta.validation.constraints.Positive;

public record CreateLoanRequest(
        @Positive
        Integer bookId
){
}
