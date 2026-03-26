package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.model.Book;
import com.gsilverio.simpleapi.model.dto.request.book.BookFilterRequest;
import com.gsilverio.simpleapi.model.dto.request.book.BookRequest;
import com.gsilverio.simpleapi.model.dto.response.book.GetByIdResponse;
import com.gsilverio.simpleapi.repository.BookRepository;
import com.gsilverio.simpleapi.repository.specification.BookSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;

    public Page<Book> listAll(BookFilterRequest filters, Pageable pageable) {
        return repository.findAll(BookSpecification.withFilter(filters), pageable);
    }

    public GetByIdResponse getById(Integer bookId) {
        Book book = repository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found"));
        return new GetByIdResponse(book);
    }

    public Book getByIsbn(String isbn){
        return repository.findByIsbn(isbn).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found"));
    }

    @Transactional
    public Book create(BookRequest request){
        Book book = new Book();

        var createdBook = getByIsbn(request.isbn());

        if (createdBook != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "isbn already in use");

        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setIsbn(request.isbn().replace("-", ""));
        book.setPublicationYear(request.publicationYear());
        book.setAvailableUnits(request.availableUnits());
        book.setCategory(request.category());

        return repository.save(book);
    }
}
