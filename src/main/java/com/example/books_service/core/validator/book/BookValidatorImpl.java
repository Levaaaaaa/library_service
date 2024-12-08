package com.example.books_service.core.validator.book;

import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.validator.ValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
class BookValidatorImpl implements BookValidator{
    @Override
    public Set<ValidationError> validate(Book book) {
        try (ValidatorFactory vf = Validation.buildDefaultValidatorFactory()) {
            Validator validator = vf.getValidator();
            return validator.validate(book).stream()
                    .map(ConstraintViolation::getMessage)
                    .map(ValidationError::new)
                    .collect(Collectors.toSet());
        }
    }
}
