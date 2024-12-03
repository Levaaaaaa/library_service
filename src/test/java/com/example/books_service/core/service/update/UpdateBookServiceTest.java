package com.example.books_service.core.service.update;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.BookMask;
import com.example.books_service.core.dto.request.IsbnRequest;
import com.example.books_service.core.dto.request.UpdateBookRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.service.find.FindBookByIsbnService;
import com.example.books_service.core.utils.BuildBookByMaskService;
import com.example.books_service.core.utils.SaveBookService;
import com.example.books_service.core.validator.ValidationError;
import com.example.books_service.core.validator.update.UpdateRequestValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UpdateBookServiceTest {
    @InjectMocks
    private UpdateBookServiceImpl service;

    @Mock
    private UpdateRequestValidator validator;

    @Mock
    private SaveBookService saveBookService;

    @Mock
    private FindBookByIsbnService findBookService;

    @Mock
    private BuildBookByMaskService buildBookByMaskService;

    private static IsbnRequest isbn;
    private static BookMask mask;
    private static UpdateBookRequest request;
    private static Book book;

    @BeforeAll
    public static void init() {
        isbn = new IsbnRequest();
        mask = new BookMask();
        request = new UpdateBookRequest(mask, isbn);
        book = new Book();
    }
    @Test
    public void notNull() {
        assertNotNull(service);
    }

    //correct update
    @Test
    public void correctUpdating() {
        when(validator.validate(request)).thenReturn(Set.of());
        when(saveBookService.saveBook(book)).thenReturn(book);
        when(findBookService.findByIsbn(isbn)).thenReturn(new CommonResponse(book, Set.of()));
        when(buildBookByMaskService.buildBook(book, mask)).thenReturn(book);

        CommonResponse response = service.updateBook(request);
        assertNotNull(response);
        assertTrue(response.getErrors().isEmpty());
        assertEquals(book, response.getBook());
    }

    //validation error
    @Test
    public void invalidRequest() {
        String expectedError = "Validation error";
        when(validator.validate(request)).thenReturn(Set.of(new ValidationError(expectedError)));

        CommonResponse response = service.updateBook(request);
        assertNotNull(response);
        assertFalse(response.getErrors().isEmpty());
        assertNull(response.getBook());
        assertEquals(1, response.getErrors().size());
        assertEquals(expectedError, response.getErrors().stream().findFirst().get().getDescription());
    }

    //book not found
    @Test
    public void bookNotFound() {
        String expectedError = "Book not found";
        when(validator.validate(request)).thenReturn(Set.of());
        when(findBookService.findByIsbn(isbn)).thenReturn(
                new CommonResponse(
                        null,
                        Set.of(
                                new ValidationError(expectedError)
                        )
                )
        );

        CommonResponse response = service.updateBook(request);
        assertNotNull(response);
        assertNull(response.getBook());
        assertEquals(1, response.getErrors().size());
        assertEquals(expectedError, response.getErrors().stream().findFirst().get().getDescription());
    }
}
