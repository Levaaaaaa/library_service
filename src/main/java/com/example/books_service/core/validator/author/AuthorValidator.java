package com.example.books_service.core.validator.author;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.validator.ValidationError;
import jakarta.validation.ConstraintViolation;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface AuthorValidator {
    public Set<ValidationError> validate(Author author);
}
