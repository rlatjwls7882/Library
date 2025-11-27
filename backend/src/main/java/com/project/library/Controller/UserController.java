package com.project.library.controller;

import com.project.library.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(HttpSession session, String isbn) {
        String userId = (String)session.getAttribute("id");
        if(userId==null || isbn==null) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(userService.borrowBook(userId, isbn));
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(HttpSession session, String isbn) {
        String userId = (String)session.getAttribute("id");
        if(userId==null || isbn==null) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(userService.returnBook(userId, isbn));
    }

    @GetMapping("/book")
    public ResponseEntity<?> searchBook(String isbn) {
        if(isbn==null) return ResponseEntity.ok(Boolean.FALSE);
        var book = userService.getBookInfo(isbn);
        if(book==null) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/book")
    public ResponseEntity<?> addBook(String isbn, String title, String description, String author) {
        if(isbn==null || title==null || author==null) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(userService.addBook(isbn, description, title, author));
    }

    @DeleteMapping("/book")
    public ResponseEntity<?> deleteBook(String isbn) {
        if (isbn==null) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(userService.deleteBook(isbn));
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(userService.getAllBooks());
    }
}
