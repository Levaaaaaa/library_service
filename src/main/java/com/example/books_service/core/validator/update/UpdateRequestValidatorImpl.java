package com.example.books_service.core.validator.update;

import com.example.books_service.core.dto.request.UpdateBookRequest;
import com.example.books_service.core.validator.ValidationError;
import com.example.books_service.core.validator.find.IsbnRequestValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
class UpdateRequestValidatorImpl implements UpdateRequestValidator{
    @Autowired
    private BookMaskValidator bookMaskValidator;

    @Autowired
    private IsbnRequestValidator isbnRequestValidator;
    @Override
    public Set<ValidationError> validate(UpdateBookRequest request) {
        if (request == null) {
            return Set.of(new ValidationError("Update Book request is null"));
        }
        try (ValidatorFactory vf = Validation.buildDefaultValidatorFactory()) {
            Validator validator = vf.getValidator();
            Set<ConstraintViolation<UpdateBookRequest>> cv = validator.validate(request);
            Set<ValidationError> result = cv.stream().map(ConstraintViolation::getMessage).map(ValidationError::new).collect(Collectors.toSet());
            if (request.getBookMask() != null) {
                result.addAll(bookMaskValidator.validate(request.getBookMask()));
            }
            if (request.getIsbn() != null) {
                result.addAll(isbnRequestValidator.validate(request.getIsbn()));
            }
            return result;
        }
    }
}
