package com.gsilverio.simpleapi.model.dto.request.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookRequest(
        @NotBlank
        String title,

        @NotBlank
        String author,

        @NotBlank
        @Size(max = 13)
        String isbn,

        Short publicationYear,
        Integer availableUnits,
        String category
) {
}
