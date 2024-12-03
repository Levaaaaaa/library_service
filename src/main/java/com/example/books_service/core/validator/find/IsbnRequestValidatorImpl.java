package com.example.books_service.core.validator.find;

import com.example.books_service.core.dto.request.IsbnRequest;
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
    public Set<ValidationError> validate(IsbnRequest request) {
        if (request == null) {
            return Set.of(new ValidationError("Isbn request must not be null"));
        }
        try (ValidatorFactory vf = Validation.buildDefaultValidatorFactory()) {
            Validator validator = vf.getValidator();
            Set<ConstraintViolation<IsbnRequest>> cv = validator.validate(request);
            return cv.stream()
                    .map(ConstraintViolation::getMessage)
                    .map(ValidationError::new)
                    .collect(Collectors.toSet());
        }
    }
}
