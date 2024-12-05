package com.example.books_service.core.service.add;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.request.AddBookRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.model.repos.AuthorRepository;
import com.example.books_service.core.model.repos.BookRepository;
import com.example.books_service.core.model.repos.GenreRepository;
import com.example.books_service.core.utils.EntityConverter;
import com.example.books_service.core.validator.BookAndAuthorValidator;
import com.example.books_service.core.validator.ValidationError;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
class AddBookServiceImpl implements AddBookService{
    @Autowired
    private BookAndAuthorValidator validator;

    @Autowired
    private BookRepository repository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;
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

        BookEntity bookEntity = entityConverter.toEntity(request.getBook());
        Optional<AuthorEntity> authorOptional = authorRepository.findByFirstNameAndLastName(request.getBook().getAuthor().getFirstName(), request.getBook().getAuthor().getLastName());
        if (authorOptional.isEmpty()) {
            AuthorEntity authorEntity = entityConverter.toEntity(request.getBook().getAuthor());
            authorRepository.save(authorEntity);
            bookEntity.setAuthor(authorEntity);
            //return buildResponseWithErrors(Set.of(new ValidationError("Author not found")));
        }


        Set<Optional<GenreEntity>> genreOptional = request.getBook().getGenres().stream().map(genreRepository::findByGenre).filter(Optional::isEmpty).collect(Collectors.toSet());
        if (!genreOptional.isEmpty()) {
            List<GenreEntity> genreEntityList = request.getBook().getGenres().stream().map(entityConverter::toEntity).toList();
            genreEntityList.forEach(genreRepository::save);
            bookEntity.setGenres(genreEntityList);
//            return buildResponseWithErrors(Set.of(new ValidationError("Genre not found!")));
        }
        repository.save(bookEntity);
        return buildSuccessfulResponse(request);
    }

    private CommonResponse buildSuccessfulResponse(AddBookRequest request) {
        return new CommonResponse(request.getBook(), Set.of());
    }

    private CommonResponse buildResponseWithErrors(Set<ValidationError> errors) {
        return new CommonResponse(null, errors);
    }


}
