package com.example.books_service.core.response;

import com.example.books_service.core.dto.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBookResponse {
    private Book book;

}
