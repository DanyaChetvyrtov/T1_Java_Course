package ru.ex.clientms.exception.custom;

public class UserIsBlocked extends RuntimeException {

    public UserIsBlocked() {
        super("User is blocked");
    }

    public UserIsBlocked(String message) {
        super(message);
    }
}
