package com.example.books_service.core.service.find;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.builder.AuthorBuilder;
import com.example.books_service.core.dto.builder.BookBuilder;
import com.example.books_service.core.dto.request.FindBookByIsbnRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.model.repos.FindBookByIsbnRepository;
import com.example.books_service.core.validator.ValidationError;
import com.example.books_service.core.validator.find.IsbnRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
class FindBookByIsbnServiceImpl implements FindBookByIsbnService{
    @Autowired
    private FindBookByIsbnRepository repository;

    @Autowired
    private IsbnRequestValidator validator;

    @Override
    public CommonResponse findByIsbn(FindBookByIsbnRequest request) {
        Set<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return buildResponseWithErrors(errors);
        }

        Optional<BookEntity> optional = repository.findByIsbn(request.getIsbn());
        if (optional.isEmpty()) {
            errors.add(new ValidationError("Book not found"));
            return buildResponseWithErrors(errors);
        }

        return buildSuccessfulResponse(optional.get());
    }

    private CommonResponse buildSuccessfulResponse(BookEntity entity) {
        return new CommonResponse(fromEntity(entity), Set.of());
    }

    private CommonResponse buildResponseWithErrors(Set<ValidationError> errors) {
        return new CommonResponse(null, errors);
    }

    private Author fromEntity(AuthorEntity entity) {
        return AuthorBuilder.create()
                .withFirstName(entity.getFirstName())
                .withLastName(entity.getLastName())
                .withPatronymic(entity.getPatronymic())
                .withEmail(entity.getEmail())
                .withBirthDate(entity.getBirthDate())
                .build();
    }

    private Book fromEntity(BookEntity entity) {
        return BookBuilder.create()
                .withISBN(entity.getIsbn())
                .withTitle(entity.getTitle())
                .withDescription(entity.getDescription())
                .withGenre(entity.getGenres().stream().map(GenreEntity::getGenre).toList())
                .withAuthor(fromEntity(entity.getAuthor()))
                .build();
    }
}
