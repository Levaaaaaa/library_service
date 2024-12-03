package com.example.books_service.core.dto.request;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.BookMask;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {
    @NotNull(message = "Updated book must not be null")
    private BookMask bookMask;
    @NotNull(message = "ISBN must not be null")
    private IsbnRequest isbn;
}
