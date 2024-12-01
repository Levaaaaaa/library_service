package com.example.books_service.core.repos;

import com.example.books_service.core.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FindBookRepository extends JpaRepository<BookEntity, Long> {
    public Optional<BookEntity> findById(Long id);
}
