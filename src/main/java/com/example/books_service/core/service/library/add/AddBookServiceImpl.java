package com.example.books_service.core.service.library.add;

import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.dto.library.request.AddBookRequest;
import com.example.books_service.core.dto.library.response.CommonResponse;
import com.example.books_service.core.dto.library.response.ManyBooksResponse;
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

        save(request.getBook());

        return buildSuccessfulResponse(request);
    }

    @Override
    public ManyBooksResponse addAll(List<Book> bookList) {
        Set<ValidationError> errors = bookList.stream()
                .map(AddBookRequest::new)
                .flatMap(request -> {
                    return validator.validate(request).stream();
                })
                .collect(Collectors.toSet());
        if (!errors.isEmpty()) {
            return new ManyBooksResponse(null, errors);
        }

        errors.addAll(
                bookList.stream()
                        .map(Book::getIsbn)
                        .map(repository::findByIsbn)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(book -> {
                            return "Book with ISBN " +
                                    book.getIsbn() +
                                    " already exists";
                        })
                        .map(ValidationError::new)
                        .collect(Collectors.toSet()
                        )
        );

        if (!errors.isEmpty()) {
            return new ManyBooksResponse(null, errors);
        }

        saveAll(bookList);
        return new ManyBooksResponse(bookList, null);
    }


    private CommonResponse buildSuccessfulResponse(AddBookRequest request) {
        return new CommonResponse(request.getBook(), Set.of());
    }

    private CommonResponse buildResponseWithErrors(Set<ValidationError> errors) {
        return new CommonResponse(null, errors);
    }

    private void save(Book book) {
        BookEntity bookEntity = entityConverter.toEntity(book);
        Optional<AuthorEntity> authorOptional = authorRepository.findByFirstNameAndLastName(
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName());
        if (authorOptional.isEmpty()) {
            AuthorEntity authorEntity = entityConverter.toEntity(book.getAuthor());
            authorRepository.save(authorEntity);
            bookEntity.setAuthor(authorEntity);
            //return buildResponseWithErrors(Set.of(new ValidationError("Author not found")));
        }


        Set<Optional<GenreEntity>> genreOptional = book.getGenres().stream().map(genreRepository::findByGenre).filter(Optional::isEmpty).collect(Collectors.toSet());
        if (!genreOptional.isEmpty()) {
            List<GenreEntity> genreEntityList = book.getGenres().stream().map(entityConverter::toEntity).toList();
            genreEntityList.forEach(genreRepository::save);
            bookEntity.setGenres(genreEntityList);
//            return buildResponseWithErrors(Set.of(new ValidationError("Genre not found!")));
        }
        repository.save(bookEntity);
    }

    private void saveAll(List<Book> books) {
        List<AuthorEntity> authors = books.stream()
                .map(Book::getAuthor)
                .filter(author -> {
                        return authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName()).isEmpty();
                })
                .map(entityConverter::toEntity)
                .toList();
        List<GenreEntity> genres = books.stream()
                .flatMap(book -> {
                    return book.getGenres().stream();
                })
                .collect(Collectors.toSet())
                .stream()
                .filter(genre -> {
                    return genreRepository.findByGenre(genre).isEmpty();
                })
                .map(entityConverter::toEntity)
                .toList();

        authorRepository.saveAll(authors);
        genreRepository.saveAll(genres);
        repository.saveAll(books.stream().map(entityConverter::toEntity).toList());

    }
}
