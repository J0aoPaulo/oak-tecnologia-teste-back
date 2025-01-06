package com.api.oak_store.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RequestLogin (
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String password) {
}