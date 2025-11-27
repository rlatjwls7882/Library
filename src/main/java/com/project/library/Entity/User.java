package com.project.library.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    private String userId;
    private String name;
    private String email;
    private String password;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isAdmin;

    @OneToMany
    private List<Book> borrowedBooks;
}
