package com.gsilverio.simpleapi.model;

import com.gsilverio.simpleapi.model.config.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "[User]")
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Positive
    @Column(nullable = false)
    private Integer age;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 500)
    private String password;
}
