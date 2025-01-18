package com.example.books_service.dto;

import jakarta.validation.constraints.Size;

public record GenreDTO(
        @Size(max = 30, message = "Genre text is very long")
        String genre
) {
}
