package com.tecsacadas.tecsacadasmanager.infrastructure.error.exception;

public class RelatorioNotFoundException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RelatorioNotFoundException(String message) {
        super(message);
    }
}
