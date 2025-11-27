package com.project.library.Entity;

import jakarta.persistence.Column;
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
    private String author;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isBorrowed;
}
