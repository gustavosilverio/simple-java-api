package com.gsilverio.simpleapi.model;

import com.gsilverio.simpleapi.model.config.Auditable;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Book")
public class Book extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, length = 13)
    private String isbn;

    @Column(nullable = false)
    private Short publicationYear;

    @Column(nullable = false)
    private Integer availableUnits = 0;

    private String category;
}
