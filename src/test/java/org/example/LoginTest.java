package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private Login auth;

    @BeforeEach
    public void setUp() {
        auth = new Login();
        auth.registerUser("kyl_1", "Ch&&sec@ke99!", "Keegan", "Govender");
    }

    @Test
    public void testCheckUserNameCorrect() {
        assertTrue(auth.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameIncorrect() {
        assertFalse(auth.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordComplexitySuccess() {
        assertTrue(auth.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordComplexityFailure() {
        assertFalse(auth.checkPasswordComplexity("password"));
    }

    @Test
    public void testLoginSuccess() {
        assertTrue(auth.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertEquals("A successful login", auth.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailure() {
        assertFalse(auth.loginUser("kyl_1", "wrongpass"));
        assertEquals("A failed login", auth.returnLoginStatus("kyl_1", "wrongpass"));
    }
}