package com.example.books_service.core.validator.find;

import com.example.books_service.core.dto.library.request.FindBookByIdRequest;
import com.example.books_service.core.validator.ValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
class IdValidatorImpl implements IdValidator{
    @Override
    public Set<ValidationError> validate(FindBookByIdRequest request) {
        if (request == null) {
            return Set.of(new ValidationError("Request must not be null"));
        }
        try (ValidatorFactory vf = Validation.buildDefaultValidatorFactory()) {
            Validator validator = vf.getValidator();
            return validator.validate(request)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .map(ValidationError::new)
                    .collect(Collectors.toSet());
        }
    }
}
