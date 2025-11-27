package com.project.library.Controller;

import com.project.library.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final UserService userService;
    @GetMapping("/login")
    public ResponseEntity<?> login(String id, String password, HttpSession session) {
        if(!userService.chkPassword(id, password)) return ResponseEntity.ok(Boolean.FALSE);

        session.setAttribute("id", id);
        session.setMaxInactiveInterval(60*60*24);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        if(session.getAttribute("id")==null) return ResponseEntity.ok(Boolean.FALSE);
        session.invalidate();
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(String id, String password, String name, String email) {
        if(id==null || password==null || name==null || email==null) return ResponseEntity.ok(Boolean.FALSE);
        if(userService.existsById(id)) return ResponseEntity.ok(Boolean.FALSE);
        return ResponseEntity.ok(userService.createUser(id, name, email, password));
    }
}
