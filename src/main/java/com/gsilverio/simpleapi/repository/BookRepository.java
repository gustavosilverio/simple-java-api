package com.gsilverio.simpleapi.repository;

import com.gsilverio.simpleapi.model.Book;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @NonNull
    @EntityGraph(attributePaths = "users")
    List<Book> findAll();
}
