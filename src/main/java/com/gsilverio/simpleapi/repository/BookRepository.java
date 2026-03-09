package com.gsilverio.simpleapi.repository;

import com.gsilverio.simpleapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
