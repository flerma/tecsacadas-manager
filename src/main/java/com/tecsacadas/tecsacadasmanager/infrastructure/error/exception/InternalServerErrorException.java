package com.tecsacadas.tecsacadasmanager.infrastructure.error.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }
}
