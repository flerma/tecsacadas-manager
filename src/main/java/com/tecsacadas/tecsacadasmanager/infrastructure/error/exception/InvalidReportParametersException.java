package com.tecsacadas.tecsacadasmanager.infrastructure.error.exception;

public class InvalidReportParametersException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidReportParametersException(String message) {
        super(message);
    }
}
