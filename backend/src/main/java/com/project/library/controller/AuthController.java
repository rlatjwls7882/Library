package com.project.library.controller;

import com.project.library.dto.LoginRequest;
import com.project.library.dto.RegisterRequest;
import com.project.library.entity.User;
import com.project.library.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        String id = loginRequest.id;
        String password = loginRequest.password;

        User user = userService.loginChk(id, password);
        if(user==null) return ResponseEntity.ok(Boolean.FALSE);
        session.setAttribute("id", id);
        session.setMaxInactiveInterval(60*60*24);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        if(session.getAttribute("id")==null) return ResponseEntity.ok(Boolean.FALSE);
        session.invalidate();
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        String id = registerRequest.id;
        String password = registerRequest.password;
        String name = registerRequest.name;
        String email = registerRequest.email;

        if(id==null || password==null || name==null || email==null) return ResponseEntity.ok(Boolean.FALSE);
        if(userService.existsById(id)) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(userService.createUser(id, name, email, password));
    }
}
