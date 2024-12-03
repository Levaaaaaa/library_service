package com.example.books_service.rest;

import com.example.books_service.core.dto.request.UpdateBookRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.service.update.UpdateBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateBookController {
    @Autowired
    private UpdateBookService service;

    @PostMapping(path = "rest/update/")
    public CommonResponse updateBook(@RequestBody UpdateBookRequest request) {
        return service.updateBook(request);
    }

}
