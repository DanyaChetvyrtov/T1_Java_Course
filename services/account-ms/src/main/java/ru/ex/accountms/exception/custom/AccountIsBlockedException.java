package ru.ex.accountms.exception.custom;

public class AccountIsBlockedException extends RuntimeException {
    public AccountIsBlockedException() {
        super("Account is blocked");
    }

    public AccountIsBlockedException(String message) {
        super(message);
    }
}
