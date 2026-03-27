package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.domain.Book;
import com.gsilverio.simpleapi.domain.dto.book.request.ListAllBookFilterRequest;
import com.gsilverio.simpleapi.domain.dto.book.request.CreateBookRequest;
import com.gsilverio.simpleapi.repository.BookRepository;
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

    public Page<Book> listAll(ListAllBookFilterRequest filters, Pageable pageable) {
        return repository.findAllWithFilters(
                filters.title(),
                filters.author(),
                filters.category(),
                pageable
        );
    }

    public Book findById(Integer bookId) {
        return repository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found"));
    }

    public Boolean existsByIsbn(String isbn){
        return repository.existsByIsbn(isbn);
    }

    @Transactional
    public Book save(CreateBookRequest request){
        if (existsByIsbn(request.isbn()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "isbn already in use");

        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setIsbn(request.isbn().replace("-", ""));
        book.setPublicationYear(request.publicationYear());
        book.setAvailableUnits(request.availableUnits());
        book.setCategory(request.category());

        return repository.save(book);
    }
}
