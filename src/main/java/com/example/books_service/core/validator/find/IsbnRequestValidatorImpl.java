package com.example.books_service.core.validator.find;

import com.example.books_service.core.dto.request.FindBookByIsbnRequest;
import com.example.books_service.core.validator.ValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
class IsbnRequestValidatorImpl implements IsbnRequestValidator{
    @Override
    public Set<ValidationError> validate(FindBookByIsbnRequest request) {
        if (request == null) {
            return Set.of(new ValidationError("Isbn request must not be null"));
        }
        try (ValidatorFactory vf = Validation.buildDefaultValidatorFactory()) {
            Validator validator = vf.getValidator();
            Set<ConstraintViolation<FindBookByIsbnRequest>> cv = validator.validate(request);
            return cv.stream()
                    .map(ConstraintViolation::getMessage)
                    .map(ValidationError::new)
                    .collect(Collectors.toSet());
        }
    }
}
