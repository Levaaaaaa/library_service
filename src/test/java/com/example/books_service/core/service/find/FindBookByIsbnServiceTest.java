package com.example.books_service.core.service.find;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.request.IsbnRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.model.repos.BookRepository;
import com.example.books_service.core.utils.EntityConverter;
import com.example.books_service.core.validator.ValidationError;
import com.example.books_service.core.validator.find.IsbnRequestValidator;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FindBookByIsbnServiceTest {
    @InjectMocks
    private FindBookByIsbnServiceImpl service;

    @Mock
    private BookRepository repository;

    @Mock
    private IsbnRequestValidator validator;

    @Mock
    private EntityConverter converter;

    private static IsbnRequest request;

    @BeforeAll
    public static void init() {
        request = new IsbnRequest();
    }

    @Test
    public void notNull() {
        assertNotNull(service);
    }

    @Test
    public void invalidRequestTest() {
        String description = "invalidRequest";
        when(validator.validate(request)).thenReturn(Set.of(new ValidationError(description)));
        CommonResponse response = service.findByIsbn(request);

        assertNull(response.getBook());
        assertFalse(response.getErrors().isEmpty());
        assertEquals(description, response.getErrors().stream().findFirst().get().getDescription());
    }

    @Test
    public void bookNotFoundTest() {
        Long id = 0L;
        String description = "Book not found";
        when(validator.validate(request)).thenReturn(new HashSet<>());
        when(repository.findById(id)).thenReturn(Optional.empty());

        CommonResponse response = service.findByIsbn(request);
        assertFalse(response.getErrors().isEmpty());
        assertNull(response.getBook());
        assertEquals(1, response.getErrors().size());
        assertEquals(description, response.getErrors().stream().findFirst().get().getDescription());
    }

    @Test
    public void bookIsFoundTest() {
        String isbn = "1111111111111";
        request.setIsbn(isbn);
        String title = "title",
                description = "description",
                genre = "genre",
                authorFirstName = "firstName",
                authorLastName = "lastName";
        List<GenreEntity> genres = List.of(new GenreEntity(1L, genre));
        BookEntity entity = new BookEntity(
                1L,
                isbn,
                title,
                genres,
                description,
                new AuthorEntity(
                        1L,
                        authorFirstName,
                        authorLastName,
                        null,
                        null,
                        null)
        );

        Book book = new Book(isbn, title, List.of(genre), description,
                new Author(authorFirstName,
                        authorLastName,
                        null,
                        null,
                        null));
        when(validator.validate(request)).thenReturn(new HashSet<>());
        when(repository.findByIsbn(isbn)).thenReturn(Optional.of(entity));
        when(converter.fromEntity(entity)).thenReturn(book);

        CommonResponse response = service.findByIsbn(request);

        assertTrue(response.getErrors().isEmpty());
        assertNotNull(response.getBook());
        assertEquals(isbn, response.getBook().getIsbn());
        assertEquals(title, response.getBook().getTitle());
        assertEquals(genres.size(), response.getBook().getGenres().size());
        assertEquals(description, response.getBook().getDescription());
        assertNotNull(response.getBook().getAuthor());
        assertEquals(authorFirstName, response.getBook().getAuthor().getFirstName());
        assertEquals(authorLastName, response.getBook().getAuthor().getLastName());
    }
}
