package com.project.library.Service;

import com.project.library.Entity.Book;

import java.util.ArrayList;

public interface UserService {
    boolean chkPassword(String id, String password);
    boolean existsById(String id);
    boolean borrowBook(String userId, String isbn);
    boolean returnBook(String userId, String isbn);
    boolean addBook(String isbn, String title, String author);
    boolean deleteBook(String isbn);
    Book getBookInfo(String isbn);
    boolean createUser(String userId, String name, String email, String password);
    ArrayList<Book> getAllBooks();
}