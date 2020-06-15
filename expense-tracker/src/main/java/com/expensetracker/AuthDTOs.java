package com.expensetracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class AuthDTOs {

    @Data
    public static class RegisterRequest {
        @NotBlank
        private String username;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String password;
    }
}
