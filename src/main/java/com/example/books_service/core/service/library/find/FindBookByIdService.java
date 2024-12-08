package com.example.books_service.core.service.library.find;

import com.example.books_service.core.dto.library.request.FindBookByIdRequest;
import com.example.books_service.core.dto.library.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface FindBookByIdService {
    public CommonResponse findBookById(FindBookByIdRequest request);
}
