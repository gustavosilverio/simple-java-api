package com.gsilverio.simpleapi.repository;

import com.gsilverio.simpleapi.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @EntityGraph(attributePaths = "users")
    @Query("SELECT b FROM Book b")
    List<Book> findAllWithUsers();
}
