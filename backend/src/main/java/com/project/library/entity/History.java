package com.project.library.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String content;

    @OneToOne
    private Book book;

    public History(String content, Book book) {
        date = new Date();
        this.content = content;
        this.book = book;
    }
}
