package com.example.books_service.core.service.add;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.request.AddBookRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.repos.FindAuthorRepository;
import com.example.books_service.core.model.repos.FindBookByIsbnRepository;
import com.example.books_service.core.model.repos.FindGenreRepository;
import com.example.books_service.core.validator.BookAndAuthorValidator;
import com.example.books_service.core.validator.ValidationError;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AddBookServiceTest {
    @InjectMocks
    private AddBookServiceImpl service;

    @Mock
    private BookAndAuthorValidator validator;

    @Mock
    private FindBookByIsbnRepository repository;

    @Mock
    private FindAuthorRepository findAuthorRepository;

    @Mock
    private FindGenreRepository findGenreRepository;

    private static AddBookRequest request;
    private static Book book;
    private static String isbn;
    private static Author author;
    private CommonResponse expectedResponse;

    @BeforeAll
    public static void init() {
        book = new Book();
        isbn = "isbn";
        book.setIsbn(isbn);
        book.setGenres(List.of(""));
        author = new Author();
        author.setBirthDate(new java.util.Date(java.sql.Date.valueOf("2000-02-02").getTime()));
        book.setAuthor(author);
        request = new AddBookRequest(book);
    }

    @Test
    public void serviceNotNull() {
        assertNotNull(service);
    }
    @Test
    public void correctRequest() {
        when(validator.validate(request)).thenReturn(Set.of());
        when(repository.findByIsbn(isbn)).thenReturn(Optional.empty());
        when(findAuthorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName())).thenReturn(Optional.empty());
        when(findGenreRepository.findByGenre(book.getGenres().get(0))).thenReturn(Optional.empty());

        CommonResponse response = service.addBook(request);

        assertNotNull(response);
        assertNotEquals(null, response.getBook());
        assertEquals(0, response.getErrors().size());
    }
    @Test
    public void incorrectRequest() {
        //request = new AddBookRequest(new Book());

        when(validator.validate(request)).thenReturn(Set.of(new ValidationError("test error")));
        when(findAuthorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName())).thenReturn(Optional.empty());
        when(findGenreRepository.findByGenre(book.getGenres().get(0))).thenReturn(Optional.empty());

        CommonResponse response = service.addBook(request);

        assertNotNull(response);
        assertNull(response.getBook());
        assertEquals(1, response.getErrors().size());
    }

    @Test
    public void bookExistsTest() {
        when(validator.validate(request)).thenReturn(new HashSet<>());
        when(repository.findByIsbn(isbn)).thenReturn(Optional.of(new BookEntity()));
        when(findAuthorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName())).thenReturn(Optional.empty());
        when(findGenreRepository.findByGenre(book.getGenres().get(0))).thenReturn(Optional.empty());

        CommonResponse response = service.addBook(request);
        assertNotNull(response);
        assertNull(response.getBook());
        assertEquals(1, response.getErrors().size());
    }
}
