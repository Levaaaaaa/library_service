package com.example.books_service.core.service.find;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.model.repos.BookRepository;
import com.example.books_service.core.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FindAllBooksServiceImpl implements FindAllBooksService{
    @Autowired
    private BookRepository repository;

    @Autowired
    private EntityConverter entityConverter;
    @Override
    public List<Book> findAll() {
        return repository.findAll().stream().map(entityConverter::fromEntity).toList();
    }


}
