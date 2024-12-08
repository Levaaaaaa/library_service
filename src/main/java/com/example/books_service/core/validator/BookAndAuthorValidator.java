package com.example.books_service.core.validator;

import com.example.books_service.core.dto.library.request.AddBookRequest;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface BookAndAuthorValidator {
    public Set<ValidationError> validate(AddBookRequest request);
}
