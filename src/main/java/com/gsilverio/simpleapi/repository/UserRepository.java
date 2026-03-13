package com.gsilverio.simpleapi.repository;

import com.gsilverio.simpleapi.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "books")
    @Query("SELECT u FROM User u")
    List<User> findAllWithBooks();
}
