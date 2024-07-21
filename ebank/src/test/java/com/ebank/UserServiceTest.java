package com.ebank;

import com.ebank.exception.UserNotFoundException;
import com.ebank.model.User;
import com.ebank.repository.UserRepository;
import com.ebank.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_shouldReturnAllUsers() {
        List<User> users = Arrays.asList(
                new User(1L, "John Doe", "1234567890", "123 Street", "City", null),
                new User(2L, "Jane Doe", "0987654321", "456 Street", "City", null)
        );

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFullName());
        assertEquals("Jane Doe", result.get(1).getFullName());
    }

    @Test
    void getUserById_shouldReturnUser() {
        User user = new User(1L, "John Doe", "1234567890", "123 Street", "City", null);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
    }

    @Test
    void getUserById_shouldThrowUserNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void saveUser_shouldSaveUser() {
        User user = new User(1L, "John Doe", "1234567890", "123 Street", "City", null);

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
    }

    @Test
    void updateUser_shouldUpdateUser() {
        User user = new User(1L, "John Doe", "1234567890", "123 Street", "City", null);

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUser(1L, user);

        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
    }

    @Test
    void deleteUser_shouldDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
