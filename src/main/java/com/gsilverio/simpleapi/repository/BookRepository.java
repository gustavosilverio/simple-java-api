package com.gsilverio.simpleapi.repository;

import com.gsilverio.simpleapi.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    @Query("""
        select b from Book b where
                (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
            AND (:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')))
            AND (:category IS NULL OR LOWER(b.category) LIKE LOWER(CONCAT('%', :category, '%')))
    """)
    Page<Book> findAllWithFilters(
            String title,
            String author,
            String category,
            Pageable pageable
    );

    Boolean existsByIsbn(String isbn);
}
