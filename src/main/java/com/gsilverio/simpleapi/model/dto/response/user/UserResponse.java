package com.gsilverio.simpleapi.model.dto.response.user;

import com.gsilverio.simpleapi.model.Book;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponse(
        Integer id,
        String name,
        Integer age,
        String email,
        List<Book> books, //TODO: Mudar Book para BookResponse (join?)
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
