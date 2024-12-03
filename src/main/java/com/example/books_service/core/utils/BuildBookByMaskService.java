package com.example.books_service.core.utils;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.BookMask;
import org.springframework.stereotype.Component;

@Component
public interface BuildBookByMaskService {
    public Book buildBook(Book source, BookMask mask);
}
