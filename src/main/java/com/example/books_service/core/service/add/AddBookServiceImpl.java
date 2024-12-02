package com.example.books_service.core.service.add;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.request.AddBookRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.model.repos.FindAuthorRepository;
import com.example.books_service.core.model.repos.FindBookByIsbnRepository;
import com.example.books_service.core.model.repos.FindGenreRepository;
import com.example.books_service.core.validator.BookAndAuthorValidator;
import com.example.books_service.core.validator.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
class AddBookServiceImpl implements AddBookService{
    @Autowired
    private BookAndAuthorValidator validator;

    @Autowired
    private FindBookByIsbnRepository repository;

    @Autowired
    private FindAuthorRepository findAuthorRepository;

    @Autowired
    private FindGenreRepository findGenreRepository;

    @Override
    public CommonResponse addBook(AddBookRequest request) {
        Set<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return buildResponseWithErrors(errors);
        }

        Optional<BookEntity> optional = repository.findByIsbn(request.getBook().getIsbn());
        if (optional.isPresent()) {
            errors.add(new ValidationError("Book with this ISBN already exists"));
            return buildResponseWithErrors(errors);
        }

        repository.save(toEntity(request.getBook()));
        return buildSuccessfulResponse(request);
    }

    private CommonResponse buildSuccessfulResponse(AddBookRequest request) {
        return new CommonResponse(request.getBook(), Set.of());
    }

    private CommonResponse buildResponseWithErrors(Set<ValidationError> errors) {
        return new CommonResponse(null, errors);
    }

    private BookEntity toEntity(Book book) {
        BookEntity result = new BookEntity();
        result.setIsbn(book.getIsbn());
        result.setTitle(book.getTitle());
        result.setDescription(book.getDescription());
        result.setGenres(book.getGenres().stream().map(this::toEntity).toList());
        result.setAuthor(toEntity(book.getAuthor()));
        return result;
    }

    private AuthorEntity toEntity(Author author) {
        Optional<AuthorEntity> optional = findAuthorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
        if (optional.isPresent()) {
            return optional.get();
        }
        AuthorEntity entity = new AuthorEntity();
        entity.setFirstName(author.getFirstName());
        entity.setLastName(author.getLastName());
        entity.setPatronymic(author.getPatronymic());
        entity.setEmail(author.getEmail());
        entity.setBirthDate(new java.sql.Date(author.getBirthDate().getTime()));
        return entity;
    }

    private GenreEntity toEntity(String genre) {
        Optional<GenreEntity> optional = findGenreRepository.findByGenre(genre);
        if (optional.isPresent()) {
            return optional.get();
        }
        GenreEntity entity = new GenreEntity();
        entity.setGenre(genre);
        return entity;
    }
}
