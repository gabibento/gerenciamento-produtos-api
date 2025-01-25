package com.produtos.gerenciador.services;

import com.produtos.gerenciador.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() throws Exception {
        tokenService = new TokenService();
        var secretField = TokenService.class.getDeclaredField("secret");
        secretField.setAccessible(true);
        secretField.set(tokenService, "testSecret");
    }

    @Test
    void generateToken_ValidUser_ReturnsToken() {
        User user = new User("user123", "password123", null);

        String token = tokenService.generateToken(user);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }
    @Test
    void generateToken_ExceptionThrown_ReturnsRuntimeException() {
        User user = new User(null, "password123", null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tokenService.generateToken(user);
        });

        assertTrue(exception.getMessage().contains("Error while generating token"));
    }

    @Test
    void validateToken_ValidToken_ReturnsSubject() {
        User user = new User("user123", "password123", null);
        String token = tokenService.generateToken(user);

        String subject = tokenService.validateToken(token);

        assertNotNull(subject);
        assertEquals("user123", subject);
    }

    @Test
    void validateToken_InvalidToken_ReturnsEmptyString() {
        String invalidToken = "invalidToken";

        String subject = tokenService.validateToken(invalidToken);

        assertEquals("", subject);
    }

}
