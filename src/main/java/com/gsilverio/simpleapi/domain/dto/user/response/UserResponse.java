package com.gsilverio.simpleapi.domain.dto.user.response;

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
