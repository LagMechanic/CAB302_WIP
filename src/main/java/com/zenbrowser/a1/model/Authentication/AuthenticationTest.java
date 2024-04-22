package com.zenbrowser.a1.model.Authentication;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class AuthenticationTest {
    Authentication auth;


    /**
     * Create a new user (username1, password1)
     */
    @BeforeEach
    void setup() {
        auth = new Authentication();
        auth.signup("username1", "password1");
    }

    @Test
    void validNewUser() {
        auth.signup("username2", "password2");
        auth.login("username2", "password2");
        assertEquals(true, auth.userLoggedIn());
    }

    @Test
    void validUsernamePassword() {
        auth.login("username1", "password1");
        assertEquals(true, auth.userLoggedIn());
    }

    @Test
    void invalidUsernamePassword() {
        assertThrows(
                InvalidCredentials.class,
                () -> auth.login("not a username", "not a password")
        );
    }

    @Test
    void userAlreadyExists() {
        assertThrows(
                UserAlreadyExists.class,
                () -> auth.signup("username1", "password1")
        );
    }


    @Test
    void logout() {
        auth.login("username1", "password1");
        assertEquals(true, auth.userLoggedIn());
        auth.logout();
        assertEquals(false, auth.userLoggedIn());
    }
}
