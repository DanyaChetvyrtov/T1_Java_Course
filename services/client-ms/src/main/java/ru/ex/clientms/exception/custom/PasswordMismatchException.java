package ru.ex.clientms.exception.custom;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
        super("Passwords don't match");
    }

    public PasswordMismatchException(String message) {
        super(message);
    }
}
