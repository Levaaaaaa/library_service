package com.example.books_service.service.auth;

import com.example.books_service.dto.auth.AuthRequest;
import com.example.books_service.dto.auth.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface SignupService {
    public AuthResponse signup(AuthRequest request);
}
