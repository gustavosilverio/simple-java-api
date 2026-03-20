package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.model.Book;
import com.gsilverio.simpleapi.model.dto.request.book.BookRequest;
import com.gsilverio.simpleapi.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;

    public List<Book> listAll() {
        return repository.findAll();
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
