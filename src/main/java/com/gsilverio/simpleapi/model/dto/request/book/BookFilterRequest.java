package com.gsilverio.simpleapi.model.dto.request.book;

public record BookFilterRequest (
        String title,
        String author,
        String category
) {
}
