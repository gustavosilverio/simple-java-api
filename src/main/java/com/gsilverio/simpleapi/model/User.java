package com.gsilverio.simpleapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "[User]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @NotBlank
    @Column(name = "Name")
    private String name;

    @NotNull
    @Positive
    @Column(name = "Age")
    private Integer age;

    @NotBlank
    @Email
    @Column(name = "Email")
    private String email;

    @NotBlank
    @Column(name = "Password")
    private String password;

    @CreationTimestamp
    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
        name = "UserBook",
        joinColumns = @JoinColumn(name = "UserId"),
        inverseJoinColumns = @JoinColumn(name = "BookId")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Book> books;
}
