package com.gsilverio.simpleapi.domain.dto.loan.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateLoanRequest(
        @Positive
        @NotNull
        Integer bookId,

        @Positive
        @NotNull
        Integer userId
){
}
