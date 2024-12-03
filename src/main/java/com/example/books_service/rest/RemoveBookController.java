package com.example.books_service.rest;

import com.example.books_service.core.dto.request.IsbnRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.service.delete.DeleteBookByIsbnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveBookController {
    @Autowired
    private DeleteBookByIsbnService service;

    @GetMapping(path = "rest/remove/{isbn}")
    public CommonResponse removeBook(@PathVariable("isbn") String isbn) {
        return service.deleteBook(new IsbnRequest(isbn));
    }
}
