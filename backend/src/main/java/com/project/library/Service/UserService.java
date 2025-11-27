package com.project.library.service;

import com.project.library.entity.Book;

import java.util.ArrayList;

public interface UserService {
    boolean chkPassword(String id, String password);
    boolean existsById(String id);
    boolean borrowBook(String userId, String isbn);
    boolean returnBook(String userId, String isbn);
    boolean addBook(String isbn, String title, String description, String author);
    boolean deleteBook(String isbn);
    Book getBookInfo(String isbn);
    boolean createUser(String userId, String name, String email, String password);
    ArrayList<Book> getAllBooks();
}