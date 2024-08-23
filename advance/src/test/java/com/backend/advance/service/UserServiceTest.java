package com.backend.advance.service;

import com.backend.advance.model.User;
import com.backend.advance.repository.UserRepsitory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepsitory userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("1", "usernameTs", "passwordTs", "Administor", true);
    }

    @Test
    void testUpdateUserStatus(){
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updateUser = userService.updateUserStatus(1L, false);

        assertFalse(updateUser.isActive());
        verify(userRepository, times(1)).save(user);
    }
}
