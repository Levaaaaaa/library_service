package com.example.books_service.core.service.add;

import com.example.books_service.core.request.AddBookRequest;
import com.example.books_service.core.response.CommonResponse;
import com.example.books_service.core.validator.GeneralValidator;
import com.example.books_service.core.validator.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AddBookServiceImpl implements AddBookService{
    @Autowired
    private GeneralValidator validator;

    @Override
    public CommonResponse addBook(AddBookRequest request) {
        Set<ValidationError> errors = validator.validate(request);
        return errors.isEmpty() ? buildSuccessfulResponse(request)
                : buildResponseWithErrors(errors);
    }

    private CommonResponse buildSuccessfulResponse(AddBookRequest request) {
        return new CommonResponse(request.getBook(), Set.of());
    }

    private CommonResponse buildResponseWithErrors(Set<ValidationError> errors) {
        return new CommonResponse(null, errors);
    }
}
