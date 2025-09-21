package ru.ex.clientms.exception.custom;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist() {
        super("User already exists");
    }

    public UserAlreadyExist(String message) {
        super(message);
    }
}
