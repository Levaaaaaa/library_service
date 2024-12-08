package com.example.books_service.core.service.library.find;

import com.example.books_service.core.dto.library.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FindAllBooksService {
    public List<Book> findAll();
}
