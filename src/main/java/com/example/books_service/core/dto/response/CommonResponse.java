package com.example.books_service.core.dto.response;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.validator.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private Book book;
    private Set<ValidationError> errors;
}
