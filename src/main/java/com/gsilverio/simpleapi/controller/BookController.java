package com.gsilverio.simpleapi.controller;

import com.gsilverio.simpleapi.domain.Book;
import com.gsilverio.simpleapi.domain.dto.book.request.ListAllBookFilterRequest;
import com.gsilverio.simpleapi.domain.dto.book.request.CreateBookRequest;
import com.gsilverio.simpleapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "book")
@Tag(name = "book")
public class BookController {
    private final BookService service;

    @Operation(summary = "list all books", description = "return a list of all the books")
    @GetMapping
    public Page<Book> listAll(@ParameterObject ListAllBookFilterRequest filters, @ParameterObject Pageable pageable) {
        return service.listAll(filters, pageable);
    }

    @Operation(summary = "get book by id", description = "return a specific book")
    @GetMapping("/{bookId}")
    public Book getById(@PathVariable int bookId) {
        return service.findById(bookId);
    }

    @Operation(summary = "create a new book", description = "insert a new record of a book in the library")
    @PostMapping
    public Book create(@Valid @RequestBody CreateBookRequest request){
        return service.save(request);
    }
}
