package com.example.books_service.service.auth;

import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {
    public String generateToken(String username);
}
