package com.project.library.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
    @Id
    private String isbn;
    private String title;
    private String description;
    private String author;
    private boolean isBorrowed;

    public Book() {
        isBorrowed=false;
    }
    public Book(String isbn, String title, String description, String author) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.author = author;
        this.isBorrowed = false;
    }
}
