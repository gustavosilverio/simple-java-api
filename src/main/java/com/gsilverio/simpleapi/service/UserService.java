package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.model.Book;
import com.gsilverio.simpleapi.model.User;
import com.gsilverio.simpleapi.repository.UserRepository;
import io.jsonwebtoken.security.Password;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll() {
        return repository.findAll();
    }

    public User findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found with id: " + id));
    }

    public User findByEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found with e-mail: " + email));
    }

    public User create(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        return repository.save(user);
    }

    @Transactional
    public User addBookToUser(Integer userId, Integer bookId){
        User user = findById(userId);
        Book book = bookService.findById(bookId);

        if (!user.getBooks().contains(book))
            user.getBooks().add(book);

        return repository.save(user);
    }
}