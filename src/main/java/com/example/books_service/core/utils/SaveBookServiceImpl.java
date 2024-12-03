package com.example.books_service.core.utils;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.model.repos.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
class SaveBookServiceImpl implements SaveBookService{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityConverter converter;
    @Override
    public Book saveBook(Book book) {
        bookRepository.save(converter.toEntity(book));
        return book;
    }
}
