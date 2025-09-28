package ru.ex.clientms.exception.custom;

public class ClientNotFound extends RuntimeException {

    public ClientNotFound() {
        super("Client not found");
    }

    public ClientNotFound(String message) {
        super(message);
    }
}
