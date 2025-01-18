package com.example.books_service.utils;

import com.example.books_service.validator.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationException extends Exception{
    private List<ValidationError> violations;
}
