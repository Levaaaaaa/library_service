package com.example.books_service.core.dto.builder;

import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public class BookBuilder {
    private String isbn;
    private String title;
    private List<String> genre;
    private String description;
    private Author author;

    public static BookBuilder create() {
        return new BookBuilder();
    }

    public Book build() {
        return new Book(this.isbn,
                this.title,
                this.genre,
                this.description,
                this.author);
    }

    public BookBuilder withISBN(String val) {
        this.isbn = val;
        return this;
    }

    public BookBuilder withTitle(String val) {
        this.title = val;
        return this;
    }

    public BookBuilder withGenre(List<String> val) {
        this.genre = val;
        return this;
    }

    public BookBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public BookBuilder withAuthor(Author val) {
        this.author = val;
        return this;
    }
}
