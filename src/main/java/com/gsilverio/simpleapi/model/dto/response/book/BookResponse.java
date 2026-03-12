package com.gsilverio.simpleapi.model.dto.response.book;

import com.gsilverio.simpleapi.model.User;

import java.time.LocalDateTime;
import java.util.List;

public record BookResponse(
        Integer id,
        String name,
        String description,
        List<User> users, //TODO: Mudar User para UserResponse (join?)
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
