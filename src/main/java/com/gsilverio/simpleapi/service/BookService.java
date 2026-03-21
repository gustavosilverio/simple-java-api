package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.model.Book;
import com.gsilverio.simpleapi.model.dto.request.book.BookRequest;
import com.gsilverio.simpleapi.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;

    public Page<Book> listAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return repository.findAll(pageable);
    }

    @Transactional
    public Book create(BookRequest request){
        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setIsbn(request.isbn());
        book.setPublicationYear(request.publicationYear());
        book.setAvailableUnits(request.availableUnits());
        book.setCategory(request.category());

        return repository.save(book);
    }
}
