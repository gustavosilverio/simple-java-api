package com.gsilverio.simpleapi.service;

import com.gsilverio.simpleapi.domain.User;
import com.gsilverio.simpleapi.domain.dto.user.request.UserRequest;
import com.gsilverio.simpleapi.domain.dto.user.response.UserResponse;
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

    private final PasswordEncoder passwordEncoder;

    //region private methods
    private UserResponse userToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
    //endregion

    public User findUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }

    public List<UserResponse> listAll() {
        return repository.findAll().stream()
                .map(this::userToUserResponse).toList();
    }

    public User findById(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }

    @Transactional
    public UserResponse save(UserRequest request) {
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
}