package com.gsilverio.simpleapi.controller;

import com.gsilverio.simpleapi.model.Book;
import com.gsilverio.simpleapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "book")
@Tag(name = "Book")
public class BookController {
    @Autowired
    private BookService service;

    @Operation(summary = "list all books", description = "return a list of all the books")
    @ApiResponse(responseCode = "200", description = "list returned with success")
    @GetMapping
    public List<Book> listAll() {
        return service.listAll();
    }

    @Operation(summary = "create a new book", description = "insert a new record of a book in the library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "book created with success"),
            @ApiResponse(responseCode = "400", description = "invalid data sent in the request body"),
    })
    @PostMapping
    public Book create(@RequestBody Book book){
        return service.create(book);
    }
}
