package com.backend.advance.service;

import com.backend.advance.model.User;
import com.backend.advance.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        logger.info("Fetching user by id: {}", id);
        return userRepository.findById(id);
    }

    public List<User> getUserByStatus(boolean status) {
        logger.info("Fetching user by status: {}", status);
        return userRepository.findByActive(status);
    }

    public User saveUser(User user) {
        logger.info("Saving user: {}", user);
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        logger.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    public User updateUserStatus(String id, Boolean status) {
        logger.info("Updating user status for id: {} to active: {}", id, status);
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
        user.setActive(status);
        return userRepository.save(user);
    }
}
