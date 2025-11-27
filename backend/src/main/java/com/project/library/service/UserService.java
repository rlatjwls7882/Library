package com.project.library.service;

import com.project.library.entity.Book;
import com.project.library.entity.User;

import java.util.ArrayList;

public interface UserService {
    User loginChk(String id, String password);
    boolean existsById(String id);
    boolean borrowBook(String id, String isbn);
    boolean returnBook(String id, String isbn);
    boolean addBook(String isbn, String title, String description, String author);
    boolean deleteBook(String isbn);
    Book getBookInfo(String isbn);
    boolean createUser(String id, String name, String email, String password);
    ArrayList<Book> getAllBooks();
}