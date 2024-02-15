package com.tecsacadas.tecsacadasmanager.infrastructure.error.exception;

import java.io.IOException;

public class SaveExcelFileExceptionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SaveExcelFileExceptionException(String message, IOException e) {
        super(message);
    }
}
