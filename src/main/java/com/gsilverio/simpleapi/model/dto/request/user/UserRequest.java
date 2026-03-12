package com.gsilverio.simpleapi.model.dto.request.user;

public record UserRequest(
        String name,
        Integer age,
        String email,
        String password
) {
}
