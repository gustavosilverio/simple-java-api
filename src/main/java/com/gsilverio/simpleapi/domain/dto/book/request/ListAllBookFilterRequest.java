package com.gsilverio.simpleapi.domain.dto.book.request;

public record ListAllBookFilterRequest(
        String title,
        String author,
        String category
) {
}
