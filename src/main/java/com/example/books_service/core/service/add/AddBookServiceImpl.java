package com.example.books_service.core.service.add;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.request.AddBookRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.model.repos.FindAuthorRepository;
import com.example.books_service.core.model.repos.BookRepository;
import com.example.books_service.core.model.repos.FindGenreRepository;
import com.example.books_service.core.utils.EntityConverter;
import com.example.books_service.core.validator.BookAndAuthorValidator;
import com.example.books_service.core.validator.ValidationError;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Transactional
@Service
class AddBookServiceImpl implements AddBookService{
    @Autowired
    private BookAndAuthorValidator validator;

    @Autowired
    private BookRepository repository;

    @Autowired
    private EntityConverter entityConverter;

    @Override
    public CommonResponse addBook(AddBookRequest request) {
        Set<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return buildResponseWithErrors(errors);
        }

        Optional<BookEntity> optional = repository.findByIsbn(request.getBook().getIsbn());
        if (optional.isPresent()) {
            return buildResponseWithErrors(Set.of(new ValidationError("Book with this ISBN already exists")));
        }

        repository.save(entityConverter.toEntity(request.getBook()));
        return buildSuccessfulResponse(request);
    }

    private CommonResponse buildSuccessfulResponse(AddBookRequest request) {
        return new CommonResponse(request.getBook(), Set.of());
    }

    private CommonResponse buildResponseWithErrors(Set<ValidationError> errors) {
        return new CommonResponse(null, errors);
    }


}
