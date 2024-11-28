package com.example.books_service.validator.book;

import com.example.books_service.core.dto.Book;
import com.example.books_service.validator.ValidationError;
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
    public Set<ValidationError<Book>> validate(Book book) {
        try (ValidatorFactory vf = Validation.buildDefaultValidatorFactory()) {
            Validator validator = vf.getValidator();
            Set<ConstraintViolation<Book>> violations = validator.validate(book);
            return violations.stream().map(ValidationError::new).collect(Collectors.toSet());
        }
    }
}
