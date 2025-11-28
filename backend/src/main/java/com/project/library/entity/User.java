package com.project.library.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;

    @OneToMany
    private List<Book> borrowedBooks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<History> histories = new ArrayList<>();

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        isAdmin = false;
    }
}
