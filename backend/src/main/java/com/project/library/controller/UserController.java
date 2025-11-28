package com.project.library.controller;

import com.project.library.dto.*;
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
    public ResponseEntity<?> borrowBook(@RequestBody BorrowBookRequest borrowBookRequest, HttpSession session) {
        String isbn = borrowBookRequest.isbn;
        String userId = (String)session.getAttribute("id");
        if(userId==null || isbn==null) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(userService.borrowBook(userId, isbn));
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody ReturnBookRequest returnBookRequest, HttpSession session) {
        String isbn = returnBookRequest.isbn;
        String userId = (String)session.getAttribute("id");
        if(userId==null || isbn==null) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(userService.returnBook(userId, isbn));
    }

    @GetMapping("/book")
    public ResponseEntity<?> searchBook(@RequestBody SearchBookRequest searchBookRequest) {
        String isbn = searchBookRequest.isbn;
        if(isbn==null) return ResponseEntity.ok(Boolean.FALSE);
        var book = userService.getBookInfo(isbn);
        if(book==null) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/book")
    public ResponseEntity<?> addBook(@RequestBody CreateBookRequest createBookRequest) {
        String isbn = createBookRequest.isbn;
        String title = createBookRequest.title;
        String description = createBookRequest.description;
        String author = createBookRequest.author;
        if(isbn==null || title==null || author==null) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(userService.addBook(isbn, description, title, author));
    }

    @DeleteMapping("/book")
    public ResponseEntity<?> deleteBook(@RequestBody DeleteBookRequest deleteBookRequest) {
        String isbn = deleteBookRequest.isbn;
        if (isbn==null) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(userService.deleteBook(isbn));
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(userService.getAllBooks());
    }

    @GetMapping("/histories")
    public ResponseEntity<?> getAllHistories() {
        return ResponseEntity.ok(userService.getAllHistorys());
    }
}
