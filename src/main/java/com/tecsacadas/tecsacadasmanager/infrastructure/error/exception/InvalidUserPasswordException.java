package com.tecsacadas.tecsacadasmanager.infrastructure.error.exception;

public class InvalidUserPasswordException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidUserPasswordException(String message) {
        super(message);
    }
}
