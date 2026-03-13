package com.gsilverio.simpleapi.model.dto.response.user;

public record UserSummaryResponse(
        Integer id,
        String name,
        String email
) {
}
