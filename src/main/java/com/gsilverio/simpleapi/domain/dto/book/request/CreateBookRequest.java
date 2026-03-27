package com.gsilverio.simpleapi.domain.dto.book.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateBookRequest(
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
