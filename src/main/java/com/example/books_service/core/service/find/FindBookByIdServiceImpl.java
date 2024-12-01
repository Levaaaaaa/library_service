package com.example.books_service.core.service.find;

import com.example.books_service.core.domain.AuthorEntity;
import com.example.books_service.core.domain.BookEntity;
import com.example.books_service.core.domain.GenreEntity;
import com.example.books_service.core.dto.Author;
import com.example.books_service.core.dto.Book;
import com.example.books_service.core.repos.FindBookRepository;
import com.example.books_service.core.request.FindBookByIdRequest;
import com.example.books_service.core.response.CommonResponse;
import com.example.books_service.core.validator.ValidationError;
import com.example.books_service.core.validator.find.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FindBookByIdServiceImpl implements FindBookByIdService{
    @Autowired
    private FindBookRepository repository;

    @Autowired
    private IdValidator validator;

    @Override
    public CommonResponse findBookById(FindBookByIdRequest request) {
        Set<ValidationError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Optional<BookEntity> optional = repository.findById(request.getId());
            if (optional.isEmpty()) {
                    errors.add(new ValidationError("Book not found"));
                    return buildResponseWithErrors(errors);
            }
            else {
                return buildSuccessfulResponse(optional.get());
            }
        }
        return buildResponseWithErrors(errors);
    }

    private CommonResponse buildSuccessfulResponse(BookEntity bookEntity) {
        Book book = new Book();
        book.setIsbn(bookEntity.getIsbn());
        book.setTitle(bookEntity.getTitle());
        book.setDescription(bookEntity.getDescription());

        Author author = new Author();
        AuthorEntity authorEntity = bookEntity.getAuthor();
        author.setFirstName(authorEntity.getFirstName());
        author.setLastName(authorEntity.getLastName());
        author.setPatronymic(authorEntity.getPatronymic());
        author.setBirthDate(authorEntity.getBirthDate());
        author.setEmail(authorEntity.getEmail());

        book.setAuthor(author);

        book.setGenres(bookEntity.getGenres().stream().map(GenreEntity::getGenre).toList());
        return new CommonResponse(book, Set.of());
    }

    private CommonResponse buildResponseWithErrors(Set<ValidationError> errors) {
        return new CommonResponse(null, errors);
    }

}
