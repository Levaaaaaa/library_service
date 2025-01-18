package com.example.books_service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotEmpty(message = "Username is empty")
    private String username;
    @NotEmpty(message = "Password is empty")
    private String password;
}
