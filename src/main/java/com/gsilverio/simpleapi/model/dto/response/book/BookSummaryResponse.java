package com.gsilverio.simpleapi.model.dto.response.book;

public record BookSummaryResponse(
        Integer id,
        String name,
        String description
) {
}
