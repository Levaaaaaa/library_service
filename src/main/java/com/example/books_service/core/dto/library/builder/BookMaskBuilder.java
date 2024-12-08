package com.example.books_service.core.dto.library.builder;

import com.example.books_service.core.dto.library.Author;
import com.example.books_service.core.dto.library.BookMask;

import java.util.List;

public class BookMaskBuilder
{
    private String title;
    private List<String> genres;
    private String description;
    private Author author;

    public static BookMaskBuilder create() {
        return new BookMaskBuilder();
    }

    public BookMask build() {
        return new BookMask(
                title,
                genres,
                description,
                author);
    }

    public BookMaskBuilder withTitle(String val) {
        this.title = val;
        return this;
    }

    public BookMaskBuilder withGenre(List<String> val) {
        this.genres = val;
        return this;
    }

    public BookMaskBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public BookMaskBuilder withAuthor(Author val) {
        this.author = val;
        return this;
    }
}
