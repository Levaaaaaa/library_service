package com.example.books_service.core.service.add;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AddBookServiceTest {
    @InjectMocks
    private AddBookServiceImpl service;

    @Mock
    private BookAndAuthorValidator validator;

    @Mock
    private BookRepository repository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private EntityConverter entityConverter;

    private static AddBookRequest request;
    private static Book book;
    private static String isbn;
    private CommonResponse expectedResponse;
    private static BookEntity bookEntity;
    private static String authorFirstName;
    private static String authorLastName;
    private static String genre;

    @BeforeAll
    public static void init() {
        book = new Book();
        isbn = "isbn";
        book.setIsbn(isbn);
        request = new AddBookRequest(book);
        bookEntity = new BookEntity();
        authorFirstName = "";
        authorLastName = "";
        genre = "";
        Author author = new Author();
        author.setFirstName(authorFirstName);
        author.setLastName(authorLastName);
        book.setAuthor(author);
        book.setGenres(List.of(genre));
    }

    @Test
    public void serviceNotNull() {
        assertNotNull(service);
    }
    @Test
    public void correctRequest() {
        when(validator.validate(request)).thenReturn(Set.of());
        when(repository.findByIsbn(isbn)).thenReturn(Optional.empty());
        when(entityConverter.toEntity(book)).thenReturn(bookEntity);
        when(authorRepository.findByFirstNameAndLastName(authorFirstName, authorLastName)).thenReturn(Optional.of(new AuthorEntity()));
        when(genreRepository.findByGenre(genre)).thenReturn(Optional.of(new GenreEntity()));

        CommonResponse response = service.addBook(request);

        assertNotNull(response);
        assertNotEquals(null, response.getBook());
        assertEquals(0, response.getErrors().size());
    }
    @Test
    public void incorrectRequest() {
        //request = new AddBookRequest(new Book());

        when(validator.validate(request)).thenReturn(Set.of(new ValidationError("test error")));
        when(entityConverter.toEntity(book)).thenReturn(bookEntity);

        CommonResponse response = service.addBook(request);

        assertNotNull(response);
        assertNull(response.getBook());
        assertEquals(1, response.getErrors().size());
    }

    @Test
    public void bookExistsTest() {
        when(validator.validate(request)).thenReturn(new HashSet<>());
        when(repository.findByIsbn(isbn)).thenReturn(Optional.of(new BookEntity()));
        when(entityConverter.toEntity(book)).thenReturn(bookEntity);

        CommonResponse response = service.addBook(request);
        assertNotNull(response);
        assertNull(response.getBook());
        assertEquals(1, response.getErrors().size());
    }
}
