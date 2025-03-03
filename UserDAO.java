package org.hcioroch.model;

public class UserDAO {
    // Stałe dane logowania – ustaw własne wartości
    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "admin123";

    public boolean validateUser(String username, String password) {
        return CORRECT_USERNAME.equals(username) && CORRECT_PASSWORD.equals(password);
    }
}
