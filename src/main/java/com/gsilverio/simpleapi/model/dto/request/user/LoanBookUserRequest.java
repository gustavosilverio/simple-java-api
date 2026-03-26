package com.gsilverio.simpleapi.model.dto.request.user;

import jakarta.validation.constraints.Positive;

public record LoanBookUserRequest(
        @Positive
        Integer bookId,

        @Positive
        Integer userId
) {
}
