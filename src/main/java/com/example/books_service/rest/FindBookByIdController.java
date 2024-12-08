package com.example.books_service.rest;

import com.example.books_service.core.dto.library.request.FindBookByIdRequest;
import com.example.books_service.core.dto.library.response.CommonResponse;
import com.example.books_service.core.service.library.find.FindBookByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class FindBookByIdController {
    @Autowired
    private FindBookByIdService service;

    @PostMapping("rest/findby/id")
    public CommonResponse find(@RequestBody FindBookByIdRequest request) {
        return service.findBookById(request);
    }
}
