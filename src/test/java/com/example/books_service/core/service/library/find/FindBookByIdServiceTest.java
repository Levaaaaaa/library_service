package com.example.books_service.core.service.library.find;

import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.domain.GenreEntity;
import com.example.books_service.core.dto.library.request.FindBookByIdRequest;
import com.example.books_service.core.dto.library.response.CommonResponse;
import com.example.books_service.core.model.repos.BookRepository;
import com.example.books_service.core.service.library.find.FindBookByIdServiceImpl;
import com.example.books_service.core.validator.ValidationError;
import com.example.books_service.core.validator.find.IdValidator;
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

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FindBookByIdServiceTest {
    @InjectMocks
    private FindBookByIdServiceImpl service;

    @Mock
    private BookRepository repository;

    @Mock
    private IdValidator validator;

    private static FindBookByIdRequest request;

    @BeforeAll
    public static void init() {
        request = new FindBookByIdRequest();
    }

    @Test
    public void notNullTest() {
        assertNotNull(service);
    }

    @Test
    public void invalidRequestTest() {
        String description = "invalidRequest";
        when(validator.validate(request)).thenReturn(Set.of(new ValidationError(description)));
        CommonResponse response = service.findBookById(request);

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

        CommonResponse response = service.findBookById(request);
        assertFalse(response.getErrors().isEmpty());
        assertNull(response.getBook());
        assertEquals(1, response.getErrors().size());
        assertEquals(description, response.getErrors().stream().findFirst().get().getDescription());
    }

    @Test
    public void bookIsFoundTest() {
        Long id = 0L;
        request.setId(id);
        String isbn = "isbn",
                title = "title",
                description = "description",
                genre = "genre",
                authorFirstName = "firstName",
                authorLastName = "lastName";
        List<GenreEntity> genres = List.of(new GenreEntity(1L, genre));
        BookEntity entity = new BookEntity(
                id,
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

        when(validator.validate(request)).thenReturn(new HashSet<>());
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        CommonResponse response = service.findBookById(request);

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
