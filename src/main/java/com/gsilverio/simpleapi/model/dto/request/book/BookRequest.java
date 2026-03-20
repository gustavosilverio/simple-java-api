package com.gsilverio.simpleapi.model.dto.request.book;

public record BookRequest(
        String title,
        String author,
        String isbn,
        Short publicationYear,
        Integer availableUnits,
        String category
) {
}
