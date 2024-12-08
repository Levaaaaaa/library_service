package com.example.books_service.core.service.library.update;

import com.example.books_service.core.dto.library.request.UpdateBookRequest;
import com.example.books_service.core.dto.library.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface UpdateBookService {
    public CommonResponse updateBook(UpdateBookRequest request);
}
