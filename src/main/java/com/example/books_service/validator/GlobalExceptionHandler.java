package com.example.books_service.validator;

import com.example.books_service.utils.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleNotFoundException(EntityNotFoundException e, WebRequest request) {
        return new ErrorResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException e, WebRequest request) {
        StringBuilder errors = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.append(error.getField()).append(" : ").append(error.getRejectedValue()).append(";");
        });
        return new ErrorResponse(errors.toString());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PSQLException.class)
    public ErrorResponse handlePSQLException(PSQLException e, WebRequest request) {
        return new ErrorResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstrainViolationException(ConstraintViolationException e, WebRequest request) {
        return new ErrorResponse(e.getMessage());
    }
}
