package com.backend.advance.controller;

import com.backend.advance.model.User;
import com.backend.advance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        logger.info("Received request to get all users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        logger.info("Received request to get user by id: {}", id);
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByStatus(@PathVariable boolean status) {
        logger.info("Received request to get user by id: {}", status);
        return userService.getUserByStatus(status)
                .get(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        logger.info("Received request to create user: {}", user);
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        logger.info("Received request to delete user with id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<User> updateUserStatus(@PathVariable String id, @RequestParam boolean status) {
        logger.info("Received request to update user status for id: {} to active: {}", id, status);
        User updateUser = userService.updateUserStatus(id, status);
        return ResponseEntity.ok(updateUser);
    }
}
