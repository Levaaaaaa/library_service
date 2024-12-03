package com.example.books_service.core.service.find;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.model.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FindAllBooksService {
    public List<Book> findAll();
}
