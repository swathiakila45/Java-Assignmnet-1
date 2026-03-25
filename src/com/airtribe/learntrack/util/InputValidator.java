package src.com.airtribe.learntrack.util;

import src.com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {
    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidInputException("Email cannot be blank.");
        }
        int atIndex = email.indexOf("@");

        if (atIndex < 1) {
            throw new InvalidInputException("Email is not valid: " + email);
        }

        String domainPart = email.substring(atIndex + 1);

        if (!domainPart.contains(".")) {
            throw new InvalidInputException("Email is not valid: " + email);
        }
    }
}