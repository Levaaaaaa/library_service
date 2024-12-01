package com.example.books_service.rest;

import com.example.books_service.core.dto.request.FindBookByIsbnRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.service.find.FindBookByIsbnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindBookByIsbnController {
    @Autowired
    private FindBookByIsbnService service;

    @GetMapping("rest/findby/isbn/{isbn}")
    public CommonResponse findBook(@PathVariable("isbn")String isbn) {
        return service.findByIsbn(new FindBookByIsbnRequest(isbn));
    }
}
