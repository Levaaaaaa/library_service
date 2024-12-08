package com.example.books_service.core.service.auth;

import com.example.books_service.core.dto.auth.AuthRequest;
import com.example.books_service.core.dto.auth.AuthResponse;
import com.example.books_service.core.model.domain.UserEntity;
import com.example.books_service.core.model.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class LoginServiceImpl implements LoginService {
    @Autowired
    private JwtTokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthResponse login(AuthRequest request) {
        Optional<UserEntity> optional = userRepository.findByUsername(request.getUsername());
        if (optional.isEmpty()) {
            return buildResponseWithErrors(List.of("Username not found"));
        }

        return buildSuccessfulResponse(tokenService.generateToken(request.getUsername()));
    }

    private AuthResponse buildResponseWithErrors(List<String> errors) {
        return new AuthResponse(null, errors);
    }

    private AuthResponse buildSuccessfulResponse(String token) {
        return new AuthResponse(token, null);
    }
}
