package com.example.books_service.core.service.update;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.request.UpdateBookRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface UpdateBookService {
    public CommonResponse updateBook(UpdateBookRequest request);
}
