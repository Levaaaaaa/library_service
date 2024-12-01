package com.example.books_service.core.dto.request;

import com.example.books_service.core.dto.Book;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequest {
    @NotNull(message = "Book must not be null")
    private Book book;
}
