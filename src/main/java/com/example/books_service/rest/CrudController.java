package com.example.books_service.rest;

import com.example.books_service.dto.BookDTO;
import com.example.books_service.entities.BookEntity;
import com.example.books_service.service.library.CrudService;

import com.example.books_service.utils.ValidationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/")
public class CrudController {
    @Autowired
    private CrudService crudService;

    @GetMapping("/find")
    public List<BookDTO> findAll() {
        return crudService.findAll();
    }

    @GetMapping("find/id/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") @Positive(message = "id must be positive") Long id) {
            return ResponseEntity.ok(crudService.findById(id));
    }

    @GetMapping("find/isbn/{isbn}")
    public ResponseEntity<?> findByIsbn(@PathVariable("isbn") String isbn) {
            return ResponseEntity.ok(crudService.findByIsbn(isbn));
    }

    @PostMapping("add")
    public ResponseEntity<?> addBooksList(@RequestBody @NotEmpty(message = "Empty request") List<@Valid BookDTO> books) {
        crudService.add(books);
        return ResponseEntity.ok("All books was added successfully!");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody @Valid BookDTO book) {
        crudService.updateById(id, book);
        return ResponseEntity.ok("Book with id " + id + "is updated successfully!");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        crudService.deleteById(id);
        return ResponseEntity.ok("Operation successful!");
    }
}
