package com.produtos.gerenciador.services;

import com.produtos.gerenciador.entities.user.User;
import com.produtos.gerenciador.entities.user.UserRole;
import com.produtos.gerenciador.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorizationServiceTest {

    @InjectMocks
    private AuthorizationService authorizationService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_UserFound() {
        String username = "user123";
        User mockUser = new User(username, "password123", UserRole.USER);

        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        UserDetails result = authorizationService.loadUserByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals("password123", result.getPassword());
        assertEquals(UserRole.USER, ((User) result).getRole());

        verify(userRepository, times(1)).findByUsername(username);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void loadUserByUsername_UserNotFound() {
        String username = "nonexistent";
        when(userRepository.findByUsername(username)).thenReturn(null);

        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> authorizationService.loadUserByUsername(username)
        );

        assertEquals("User not found with username: " + username, exception.getMessage());
        verify(userRepository, times(1)).findByUsername(username);
        verifyNoMoreInteractions(userRepository);
    }

}
