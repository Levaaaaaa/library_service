package com.example.books_service.core.utils;

import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.dto.library.BookMask;
import org.springframework.stereotype.Component;

@Component
public interface BuildBookByMaskService {
    public Book buildBook(Book source, BookMask mask);
}
