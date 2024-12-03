package com.example.books_service.rest;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.service.find.FindAllBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindAllBookController {
    @Autowired
    private FindAllBooksService service;

    @GetMapping("rest/findall/")
    public List<Book> findAll() {
        return service.findAll();
    }
}
