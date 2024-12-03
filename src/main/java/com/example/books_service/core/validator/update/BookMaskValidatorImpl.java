package com.example.books_service.core.validator.update;

import com.example.books_service.core.dto.BookMask;
import com.example.books_service.core.validator.ValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMaskValidatorImpl implements BookMaskValidator{
    @Override
    public Set<ValidationError> validate(BookMask bookMask) {
        try (ValidatorFactory vf = Validation.buildDefaultValidatorFactory()) {
            Validator validator = vf.getValidator();
            return validator.validate(bookMask)
                    .stream().map(ConstraintViolation::getMessage)
                    .map(ValidationError::new).collect(Collectors.toSet());
        }
    }
}
