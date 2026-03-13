package com.gsilverio.simpleapi.model.dto.response.user;

import com.gsilverio.simpleapi.model.dto.response.book.BookSummaryResponse;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponse(
        Integer id,
        String name,
        Integer age,
        String email,
        List<BookSummaryResponse> books,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
