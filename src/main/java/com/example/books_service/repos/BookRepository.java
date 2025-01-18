package com.example.books_service.repos;

import com.example.books_service.entities.BookEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    public Optional<BookEntity> findByIsbn(String isbn);
    public long deleteByIsbn(String isbn);

    @Modifying
    @Transactional
    @Query("update BookEntity b set b.title = :#{#entity.title}, " +
            "b.description = :#{#entity.description}, " +
            "b.genres = :#{#entity.genres}, " +
            "b.author = :#{#entity.author} " +
            "where b.isbn = :#{#entity.isbn}")
    public void updateBook(@Param("entity") BookEntity entity);
}
