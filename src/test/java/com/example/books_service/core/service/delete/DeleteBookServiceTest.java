package com.example.books_service.core.service.delete;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.request.IsbnRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.repos.BookRepository;
import com.example.books_service.core.service.find.FindBookByIsbnService;
import com.example.books_service.core.validator.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class DeleteBookServiceTest {
    @InjectMocks
    private DeleteBookByIsbnServiceImpl service;

    @Mock
    private BookRepository repository;

    @Mock
    private FindBookByIsbnService findBookService;


    private String isbn = "";
    private IsbnRequest request = new IsbnRequest(isbn);
    private BookEntity entity = new BookEntity();
    //not null
    @Test
    public void notNull() {
        assertNotNull(service);
    }
    //book deleted
    @Test
    public void deleteSuccess() {
        when(findBookService.findByIsbn(request)).thenReturn(new CommonResponse(new Book(), Set.of()));

        CommonResponse response = service.deleteBook(request);
        assertNotNull(response);
        assertTrue(response.getErrors().isEmpty());
        assertNotNull(response.getBook());
    }

    //book not found
    @Test
    public void bookNotFoundTest() {
        when(findBookService.findByIsbn(request)).thenReturn(new CommonResponse(null, Set.of(new ValidationError())));

        CommonResponse response = service.deleteBook(request);
        assertNotNull(response);
        assertEquals(1, response.getErrors().size());
        assertNull(response.getBook());
    }
    //invalid isbn
}
