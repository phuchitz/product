package com.backend.advance.repository;

import com.backend.advance.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    // Custom query to find all users by active status
    List<User> findByActive(boolean status);
}
