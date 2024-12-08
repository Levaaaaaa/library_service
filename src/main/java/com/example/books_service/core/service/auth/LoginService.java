package com.example.books_service.core.service.auth;

import com.example.books_service.core.dto.auth.AuthRequest;
import com.example.books_service.core.dto.auth.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    public AuthResponse login(AuthRequest request);
}
