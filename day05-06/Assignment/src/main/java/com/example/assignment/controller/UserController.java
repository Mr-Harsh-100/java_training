package com.example.assignment.controller;

import com.example.assignment.dto.UserRegisterRequest;
import com.example.assignment.model.User;
import com.example.assignment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    // ✅ Create user
    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid @RequestBody UserRegisterRequest request
    ) {
        User user = userService.createUser(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(user);
    }
}
