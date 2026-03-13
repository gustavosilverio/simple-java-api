package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.model.Book;
import com.gsilverio.simpleapi.model.dto.request.book.BookRequest;
import com.gsilverio.simpleapi.model.dto.response.book.BookResponse;
import com.gsilverio.simpleapi.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;

    public Book findBookById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found with id: " + id));
    }

    private BookResponse bookToBookResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getName(),
                book.getDescription(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }

    public List<BookResponse> listAll() {
        return repository.findAllWithUsers()
                .stream().map(this::bookToBookResponse).toList();
    }

    public BookResponse findById(Integer id) {
        return bookToBookResponse(findBookById(id));
    }

    @Transactional
    public BookResponse create(BookRequest request){
        Book book = new Book();
        book.setName(request.name());
        book.setDescription(request.description());

        return bookToBookResponse(repository.save(book));
    }
}
