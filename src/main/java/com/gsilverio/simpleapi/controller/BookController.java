package com.gsilverio.simpleapi.controller;

import com.gsilverio.simpleapi.model.Book;
import com.gsilverio.simpleapi.model.dto.request.book.BookFilterRequest;
import com.gsilverio.simpleapi.model.dto.request.book.BookRequest;
import com.gsilverio.simpleapi.model.dto.response.book.GetByIdResponse;
import com.gsilverio.simpleapi.model.dto.response.config.ApiResponse;
import com.gsilverio.simpleapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "book")
@Tag(name = "book")
public class BookController {
    @Autowired
    private BookService service;

    @Operation(summary = "list all books", description = "return a list of all the books")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> listAll(@ParameterObject BookFilterRequest filters, @ParameterObject Pageable pageable) {
        var books = service.listAll(filters, pageable);
        return ResponseEntity.ok(ApiResponse.success(books));
    }

    @Operation(summary = "get book by id", description = "return a specific book")
    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse<GetByIdResponse>> getById(@PathVariable int bookId) {
        var book = service.getById(bookId);
        return ResponseEntity.ok(ApiResponse.success(book));
    }

    @Operation(summary = "create a new book", description = "insert a new record of a book in the library")
    @PostMapping
    public ResponseEntity<ApiResponse<Book>> create(@Valid @RequestBody BookRequest request){
        var createdBook = service.create(request);
        return ResponseEntity.ok(ApiResponse.success(createdBook));
    }
}
