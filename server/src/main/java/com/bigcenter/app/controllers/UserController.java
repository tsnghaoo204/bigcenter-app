package com.bigcenter.app.controllers;

import com.bigcenter.app.dtos.requests.user.CreateUserDTO;
import com.bigcenter.app.dtos.requests.user.UpdateUserDTO;
import com.bigcenter.app.entities.User;
import com.bigcenter.app.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Create user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    // Get all users
    @GetMapping
    public ResponseEntity<Set<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    // Update user
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
