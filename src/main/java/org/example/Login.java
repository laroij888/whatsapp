package org.example;

public class Login {
    private String registeredUsername = "";
    private String registeredPassword = "";
    private String registeredFirstName = "";
    private String registeredLastName = "";

    public Login() {
    }

    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasCapital = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");

        return hasCapital && hasNumber && hasSpecial;
    }

    public String registerUser(String username, String password, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password does not meet the complexity requirements, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }

        this.registeredUsername = username;
        this.registeredPassword = password;
        this.registeredFirstName = firstName;
        this.registeredLastName = lastName;
        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        return username != null && password != null &&
               username.equals(this.registeredUsername) &&
               password.equals(this.registeredPassword);
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "A successful login";
        }
        return "A failed login";
    }

    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            return "A successful login";
        }
        return "A failed login";
    }

    public String getRegisteredUsername() { return registeredUsername; }
    public String getRegisteredPassword() { return registeredPassword; }
    public String getRegisteredFirstName() { return registeredFirstName; }
    public String getRegisteredLastName() { return registeredLastName; }
}