package com.project.library.service;

import com.project.library.entity.Book;
import com.project.library.entity.User;
import com.project.library.repository.BookRepository;
import com.project.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public boolean chkPassword(String id, String password) {
        User user = userRepository.findById(id).orElse(null);
        if(user==null) return false;
        return user.getPassword().equals(password);
    }

    @Override
    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean borrowBook(String id, String isbn) {
        User user = userRepository.findById(id).orElse(null);
        Book book = bookRepository.findById(isbn).orElse(null);
        if(user==null || book==null || book.isBorrowed()) return false;

        user.getBorrowedBooks().add(book);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean returnBook(String id, String isbn) {
        User user = userRepository.findById(id).orElse(null);
        Book book = bookRepository.findById(isbn).orElse(null);
        if(user==null || book==null || !book.isBorrowed()) return false;

        user.getBorrowedBooks().remove(book);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean addBook(String isbn, String title, String description, String author) {
        if(bookRepository.existsById(isbn)) return false;
        Book book = new Book(isbn, title, description, author);
        bookRepository.save(book);
        return true;
    }

    @Override
    public boolean deleteBook(String isbn) {
        if(!bookRepository.existsById(isbn)) return false;
        bookRepository.deleteById(isbn);
        return true;
    }

    @Override
    public Book getBookInfo(String isbn) {
        return bookRepository.findById(isbn).orElse(null);
    }

    @Override
    public boolean createUser(String id, String name, String email, String password) {
        if(userRepository.existsById(id)) return false;
        User user = new User(id, name, email, password);
        userRepository.save(user);
        return true;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }
}
