package com.example.books_service.core.service.auth;

import com.example.books_service.core.dto.User;
import com.example.books_service.core.dto.auth.AuthRequest;
import com.example.books_service.core.dto.auth.AuthResponse;
import com.example.books_service.core.model.domain.UserEntity;
import com.example.books_service.core.model.repos.UserRepository;
import com.example.books_service.core.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class SignupServiceImpl implements SignupService{
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityConverter entityConverter;

    @Override
    public AuthResponse signup(AuthRequest request) {
        Optional<UserEntity> optional = userRepository.findByUsername(request.getUsername());
        if (optional.isPresent()) {
            return buildResponseWithErrors(List.of("This user already exists"));
        }

        UserEntity entity = entityConverter.toEntity(new User(request.getUsername(), request.getPassword()));
        userRepository.save(entity);
        return loginService.login(request);
    }

    private AuthResponse buildResponseWithErrors(List<String> errors) {
        return new AuthResponse(null, errors);
    }

    private AuthResponse buildSuccessfulResponse(String token) {
        return new AuthResponse(token, null);
    }
}
