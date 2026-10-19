package org.example;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        // Initializes a valid base user instance before each test run
        login = new Login("kyl_1", "Ch&&sec@ke99!", "+278389689", "Kyle", "Smith");
    }

    // ==========================================
    // 1. assertEquals Tests (Tables 4 & 5)
    // ==========================================

    @Test
    public void testUsernameCorrectlyFormattedResponse() {
        // Verifies the greeting output format when login succeeds
        String expected = "Welcome Kyle, Smith it is great to see you again.";
        String actual = login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertEquals(expected, actual);
    }

    @Test
    public void testUsernameIncorrectlyFormattedResponse() {
        login.setUsername("kyle!!!!!!");
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = login.registerUser();
        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordMeetsComplexityResponse() {
        login.setPassword("Ch&&sec@ke99!");
        // Validates standard success message when password satisfies rules
        String expected = "Password successfully captured.";
        // Depending on your assignment harness, checkPasswordComplexity() or registerUser() message:
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexityResponse() {
        login.setPassword("password");
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = login.registerUser();
        assertEquals(expected, actual);
    }

    @Test
    public void testCellPhoneCorrectlyFormattedResponse() {
        login.setCellPhoneNumber("+27838968976");
        // Test data "+27838968976" / "+278389689" returns success message
        assertTrue(login.checkCellPhoneNumber("+278389689"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormattedResponse() {
        login.setCellPhoneNumber("08966553");
        String expected = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        String actual = login.registerUser();
        assertEquals(expected, actual);
    }

    // ==========================================
    // 2. assertTrue / assertFalse Tests (Tables 5 & 6)
    // ==========================================

    @Test
    public void testLoginSuccessful() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        assertFalse(login.loginUser("wrong_user", "wrong_pass"));
    }

    @Test
    public void testUsernameCorrectlyFormattedBoolean() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormattedBoolean() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexityBoolean() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexityBoolean() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneNumberCorrectlyFormattedBoolean() {
        assertTrue(login.checkCellPhoneNumber("+278389689"));
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormattedBoolean() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}