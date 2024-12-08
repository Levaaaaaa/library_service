package com.example.books_service.core.dto.library.builder;

import com.example.books_service.core.dto.library.Author;
import com.example.books_service.core.dto.library.Book;

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
