package com.example.books_service.validator.author;

import com.example.books_service.core.dto.Author;
import com.example.books_service.validator.ValidationError;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
class AuthorValidatorImpl implements AuthorValidator{

    @Override
    public Set<ValidationError<Author>> validation(Author author) {
        try (ValidatorFactory vf = Validation.buildDefaultValidatorFactory()) {
            Validator validator = vf.getValidator();
            return validator.validate(author).stream().map(ValidationError::new).collect(Collectors.toSet());
        }
    }
}
