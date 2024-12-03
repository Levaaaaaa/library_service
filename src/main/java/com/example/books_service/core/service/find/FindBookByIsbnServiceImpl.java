package com.example.books_service.core.service.find;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.request.IsbnRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.repos.BookRepository;
import com.example.books_service.core.utils.EntityConverter;
import com.example.books_service.core.validator.ValidationError;
import com.example.books_service.core.validator.find.IsbnRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
class FindBookByIsbnServiceImpl implements FindBookByIsbnService{
    @Autowired
    private BookRepository repository;

    @Autowired
    private IsbnRequestValidator validator;

    @Autowired
    private EntityConverter entityConverter;

    @Override
    public CommonResponse findByIsbn(IsbnRequest request) {
        Set<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return buildResponseWithErrors(errors);
        }

        Optional<BookEntity> optional = repository.findByIsbn(request.getIsbn());
        if (optional.isEmpty()) {
            errors.add(new ValidationError("Book not found"));
            return buildResponseWithErrors(errors);
        }

        return buildSuccessfulResponse(entityConverter.fromEntity(optional.get()));
    }

    private CommonResponse buildSuccessfulResponse(Book book) {
        return new CommonResponse(book, Set.of());
    }

    private CommonResponse buildResponseWithErrors(Set<ValidationError> errors) {
        return new CommonResponse(null, errors);
    }

}
