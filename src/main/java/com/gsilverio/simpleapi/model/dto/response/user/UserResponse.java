package com.gsilverio.simpleapi.model.dto.response.user;

import java.time.LocalDateTime;

public record UserResponse(
        Integer id,
        String name,
        Integer age,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
