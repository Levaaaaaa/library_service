package com.example.books_service.core.service.auth;

import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {
    public String generateToken(String username);
}
