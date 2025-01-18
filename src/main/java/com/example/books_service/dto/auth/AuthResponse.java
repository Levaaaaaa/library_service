package com.example.books_service.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private List<String> errors;
}
