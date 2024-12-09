package com.example.books_service.core.service.library.update;

import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.dto.library.BookMask;
import com.example.books_service.core.dto.library.request.UpdateBookRequest;
import com.example.books_service.core.dto.library.response.CommonResponse;
import com.example.books_service.core.model.domain.AuthorEntity;
import com.example.books_service.core.model.domain.BookEntity;
import com.example.books_service.core.model.repos.BookRepository;
import com.example.books_service.core.utils.BuildBookByMaskService;
import com.example.books_service.core.utils.EntityConverter;
import com.example.books_service.core.validator.ValidationError;
import com.example.books_service.core.validator.update.UpdateRequestValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@Transactional
class UpdateBookServiceImpl implements UpdateBookService{
    @Autowired
    private UpdateRequestValidator validator;

    @Autowired
    private BuildBookByMaskService buildBookService;


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityConverter converter;

    @Override
    public CommonResponse updateBook(UpdateBookRequest request) {
        //check request
        Set<ValidationError> errors = validator.validate(request);
        //if request valid find book for updating
        if (!errors.isEmpty()) {
            return buildResponseWithErrors(errors);
        }

        Optional<BookEntity> optional = bookRepository.findByIsbn(request.getIsbn().getIsbn());
        if (optional.isEmpty()) {
            return buildResponseWithErrors(Set.of(new ValidationError("Book not found")));
        }

        BookEntity source = optional.get();
        //if book is found - update

        BookEntity target = buildBook(source, request.getBookMask());
        bookRepository.save(target);
//        Book target = buildBookService.buildBook(response.getBook(), request.getBookMask());
        return buildSuccessfulResponse(converter.fromEntity(target));

    }

    private CommonResponse buildResponseWithErrors(Set<ValidationError> errors) {
        return new CommonResponse(null, errors);
    }

    private CommonResponse buildSuccessfulResponse(Book book) {
        return new CommonResponse(book, Set.of());
    }

    private BookEntity buildBook(BookEntity source, BookMask mask) {
        if (mask.getTitle() != null) {
            source.setTitle(mask.getTitle());
        }
        if (mask.getDescription() != null) {
            source.setDescription(mask.getDescription());
        }
        //add genres update
        if (mask.getAuthor() != null) {
            AuthorEntity author = source.getAuthor();
            if (mask.getAuthor().getFirstName() != null) {
                author.setFirstName(mask.getAuthor().getFirstName());
            }
            if (mask.getAuthor().getLastName() != null) {
                author.setLastName(mask.getAuthor().getLastName());
            }
            if (mask.getAuthor().getPatronymic() != null) {
                author.setPatronymic(mask.getAuthor().getPatronymic());
            }
            if (mask.getAuthor().getBirthDate() != null) {
                author.setBirthDate(new java.sql.Date(mask.getAuthor().getBirthDate().getTime()));
            }
            if (mask.getAuthor().getEmail() != null) {
                author.setEmail(mask.getAuthor().getEmail());
            }
            source.setAuthor(author);
        }
        return source;
    }




}
