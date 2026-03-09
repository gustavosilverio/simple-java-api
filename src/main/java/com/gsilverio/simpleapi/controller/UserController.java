package com.gsilverio.simpleapi.controller;

import com.gsilverio.simpleapi.model.User;
import com.gsilverio.simpleapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@Tag(name = "user")
public class UserController {
    @Autowired
    private UserService service;

    @Operation(summary = "list all users", description = "return a list of all the users")
    @ApiResponse(responseCode = "200", description = "list returned with success")
    @GetMapping
    public List<User> listAll() {
        return service.listAll();
    }

    @Operation(summary = "create a new user", description = "insert a new record of a user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user created with success"),
            @ApiResponse(responseCode = "400", description = "invalid data sent in the request body"),
    })
    @PostMapping
    public User create(@RequestBody User user){
        return service.create(user);
    }

    @Operation(summary = "add a book to a user", description = "insert a new record of a book that a user took in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "book added to user with success"),
            @ApiResponse(responseCode = "400", description = "invalid data sent in the request body"),
    })
    @PostMapping("/{userId}/books/{bookId}")
    public User addBook(@PathVariable Integer userId, @PathVariable Integer bookId){
        return service.addBookToUser(userId, bookId);
    }
}
