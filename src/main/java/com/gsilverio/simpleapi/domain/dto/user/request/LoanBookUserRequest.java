package com.gsilverio.simpleapi.domain.dto.user.request;

import jakarta.validation.constraints.Positive;

public record LoanBookUserRequest(
        @Positive
        Integer bookId,

        @Positive
        Integer userId
) {
}
