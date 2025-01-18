package com.example.books_service.repos;

import com.example.books_service.entities.GenreEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    public Optional<GenreEntity> findByGenre(String genre);
}
