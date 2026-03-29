package com.gsilverio.simpleapi.domain.dto.loan.response;

public record CreateLoanResponse(
        Integer id,
        Integer bookId,
        Integer userId
) {
}
