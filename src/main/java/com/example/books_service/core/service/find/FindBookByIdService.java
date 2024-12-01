package com.example.books_service.core.service.find;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.request.FindBookByIdRequest;
import com.example.books_service.core.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface FindBookByIdService {
    public CommonResponse findBookById(FindBookByIdRequest request);
}
