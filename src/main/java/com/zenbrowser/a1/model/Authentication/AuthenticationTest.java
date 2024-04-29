//package com.zenbrowser.a1.model.Authentication;
//
//import org.junit.jupiter.api.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//public class AuthenticationTest {
//    private Authentication auth;
//
//    /**
//     * Create a new user (username1, password1)
//     */
//    @BeforeEach
//    public void setup() {
//        try {
//            IUsersDAO usersDAO = new MockUsersDAO();
//            auth = new Authentication(usersDAO);
//            auth.signup("username1", "password1");
//        } catch (UserAlreadyExists e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void validNewUser() {
//
//        try {
//            auth.signup("username2", "password2");
//            auth.login("username2", "password2");
//            assertEquals(true, auth.userLoggedIn());
//        } catch (InvalidCredentials | UserAlreadyExists e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void validUsernamePassword() {
//        try {
//            auth.login("username1", "password1");
//            assertEquals(true, auth.userLoggedIn());
//        } catch (InvalidCredentials e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void invalidUsernamePassword() {
//        assertThrows(
//                InvalidCredentials.class,
//                () -> auth.login("not a username", "not a password")
//        );
//    }
//
//    @Test
//    public void userAlreadyExists() {
//        assertThrows(
//                UserAlreadyExists.class,
//                () -> auth.signup("username1", "password1")
//        );
//    }
//
//
//    @Test
//    public void logout() {
//        try {
//            auth.login("username1", "password1");
//            assertEquals(true, auth.userLoggedIn());
//            auth.logout();
//            assertEquals(false, auth.userLoggedIn());
//        } catch (InvalidCredentials e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
