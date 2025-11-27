package com.project.library.entity;

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
    private String id;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;

    @OneToMany
    private List<Book> borrowedBooks;

    public User() {
        isAdmin=false;
    }
    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        isAdmin = false;
    }
}
