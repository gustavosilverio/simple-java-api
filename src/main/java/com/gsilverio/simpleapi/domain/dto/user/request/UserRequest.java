package com.gsilverio.simpleapi.domain.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UserRequest(
        @NotBlank
        String name,

        @Positive
        Integer age,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Min(value = 6)
        String password
) {
}
