package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.model.Book;
import com.gsilverio.simpleapi.model.User;
import com.gsilverio.simpleapi.model.dto.request.user.UserRequest;
import com.gsilverio.simpleapi.model.dto.response.user.UserResponse;
import com.gsilverio.simpleapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    private final BookService bookService;

    private final PasswordEncoder passwordEncoder;

    //region private methods
    private User findUserById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found with id: " + id));
    }

    private UserResponse userToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getEmail(),
                user.getBooks(), // TODO: Para cada usuário será feito uma query para obter seus livros, ajustar
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
    //endregion

    public User findUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found with e-mail: " + email));
    }

    public List<UserResponse> listAll() {
        return repository.findAll().stream()
                .map(this::userToUserResponse).toList();
    }

    public UserResponse findById(Integer id) {
        User user = findUserById(id);
        return userToUserResponse(user);
    }

    public UserResponse findByEmail(String email){
        User user = findUserByEmail(email);
        return userToUserResponse(user);
    }

    @Transactional
    public UserResponse create(UserRequest request) {
        if(repository.findByEmail(request.email()).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "e-mail already in use");

        String encryptedPassword = passwordEncoder.encode(request.password());

        User user = new User();
        user.setName(request.name());
        user.setAge(request.age());
        user.setEmail(request.email());
        user.setPassword(encryptedPassword);

        User createdUser = repository.save(user);
        return userToUserResponse(createdUser);
    }

    @Transactional
    public UserResponse addBookToUser(Integer userId, Integer bookId){
        User user = findUserById(userId);
        Book book = bookService.findBookById(bookId);

        if (!user.getBooks().contains(book)) {
            user.getBooks().add(book);
        }

        User savedUser = repository.save(user);

        return userToUserResponse(savedUser);
    }
}