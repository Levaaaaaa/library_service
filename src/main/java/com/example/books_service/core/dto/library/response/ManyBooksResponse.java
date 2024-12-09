package com.example.books_service.core.dto.library.response;

import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.validator.ValidationError;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManyBooksResponse {
    private List<Book> books;
    private Set<ValidationError> errors;
}
