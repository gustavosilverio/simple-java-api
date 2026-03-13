package com.gsilverio.simpleapi.model.dto.response.book;

import java.time.LocalDateTime;

public record BookResponse(
        Integer id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
