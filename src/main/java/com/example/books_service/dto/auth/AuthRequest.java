package com.example.books_service.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotEmpty(message = "Username is empty")
    @Size(max = 50, message = "Username is too long")
    private String username;

    @NotEmpty(message = "Password is empty")
    @Size(max = 50, message = "Password is too long")
    private String password;
}
