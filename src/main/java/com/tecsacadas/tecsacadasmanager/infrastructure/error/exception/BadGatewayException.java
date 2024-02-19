package com.tecsacadas.tecsacadasmanager.infrastructure.error.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BadGatewayException extends RuntimeException {

    private static final long serialVersionUID = 4057410511146509889L;

    public BadGatewayException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }
}
