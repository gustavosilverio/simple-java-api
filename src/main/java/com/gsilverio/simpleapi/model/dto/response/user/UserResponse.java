package com.gsilverio.simpleapi.model.dto.response.user;

import com.gsilverio.simpleapi.model.dto.response.book.BookSummaryResponse;

import java.time.LocalDateTime;
import java.util.Set;

public record UserResponse(
        Integer id,
        String name,
        Integer age,
        String email,
        Set<BookSummaryResponse> books,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
