package  org.example;
import java.util.regex.Pattern;

public class Login {
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    // Default constructor
    public Login() {}

    // Constructor with registration fields
    public Login(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Checks if username contains an underscore and is no more than 5 characters long.
     */
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    /**
     * Overloaded method for instance state check.
     */
    public boolean checkUserName() {
        return checkUserName(this.username);
    }

    /**
     * Checks if password contains:
     * - At least 8 characters
     * - A capital letter
     * - A number
     * - A special character
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isWhitespace(ch) && !Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Overloaded method for instance state check.
     */
    public boolean checkPasswordComplexity() {
        return checkPasswordComplexity(this.password);
    }

    /**
     * Ensures cell phone conforms to:
     * International country code (+ followed by digits) followed by number, max 10 chars.
     * Regex reference: ISO/ITU-T E.164 standard formatting via Java regex Pattern.
     */
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber == null) {
            return false;
        }
        // Matches + followed by country code and local digits, total max 10 chars
        String regex = "^\\+[0-9]{1,9}$";
        return Pattern.matches(regex, cellPhoneNumber) && cellPhoneNumber.length() <= 10;
    }

    /**
     * Overloaded method for instance state check.
     */
    public boolean checkCellPhoneNumber() {
        return checkCellPhoneNumber(this.cellPhoneNumber);
    }

    /**
     * Evaluates registration status and returns the appropriate prompt.
     */
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        return "The two above conditions have been met, and the user has been registered successfully.";
    }

    /**
     * Verifies that provided credentials match registered details.
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername != null
                && enteredPassword != null
                && enteredUsername.equals(this.username)
                && enteredPassword.equals(this.password);
    }

    /**
     * Returns login message based on credentials verification.
     */
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCellPhoneNumber() { return cellPhoneNumber; }
    public void setCellPhoneNumber(String cellPhoneNumber) { this.cellPhoneNumber = cellPhoneNumber; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}