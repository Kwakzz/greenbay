package com.example.greenbay;
import com.example.greenbay.controllers.UserController;
import com.example.greenbay.models.User;
import com.example.greenbay.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        String userId = "1";
        User mockUser = new User("testuser", "test@example.com", "password");
        mockUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        User user = userController.getUser(userId);

        assertNotNull(user);
        assertEquals(userId, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    void testGetAllUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User("user1", "user1@example.com", "password1"));
        mockUsers.add(new User("user2", "user2@example.com", "password2"));

        when(userRepository.findAll()).thenReturn(mockUsers);

        Iterable<User> users = userController.getUsers();

        assertNotNull(users);
        assertEquals(2, ((List<User>) users).size());
    }

    @Test
    void testCreateUser() {
        User newUser = new User("newuser", "newuser@example.com", "newpassword");

        when(userRepository.save(newUser)).thenReturn(newUser);

        ResponseEntity<User> responseEntity = userController.createUser(newUser);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(newUser, responseEntity.getBody());
        verify(userRepository, times(1)).save(newUser);
    }
}
