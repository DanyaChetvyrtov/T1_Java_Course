package ru.ex.accountms.exception.custom;

public class AccountNotFound extends RuntimeException {

    public AccountNotFound() {
        super("Account not found");
    }

    public AccountNotFound(String message) {
        super(message);
    }
}
