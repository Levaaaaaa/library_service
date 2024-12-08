package com.example.books_service.core.utils;

import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.dto.library.BookMask;
import org.springframework.stereotype.Component;

@Component
public class BuildBookByMaskServiceImpl implements BuildBookByMaskService{
    @Override
    public Book buildBook(Book source, BookMask mask) {
        if (mask.getTitle() != null) {
            source.setTitle(mask.getTitle());
        }
        if (mask.getDescription() != null) {
            source.setDescription(mask.getDescription());
        }
        if (mask.getGenres() != null) {
            source.setGenres(mask.getGenres());
        }
        if (mask.getAuthor() != null) {
            source.setAuthor(mask.getAuthor());
        }
        return source;
    }
}
