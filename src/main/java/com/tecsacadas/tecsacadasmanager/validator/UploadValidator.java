package com.tecsacadas.tecsacadasmanager.validator;

import com.tecsacadas.tecsacadasmanager.exception.FileNotSupportedException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@NoArgsConstructor
public class UploadValidator {

    public static void validate(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType == null)
            throw new FileNotSupportedException("Tipo de arquivo não suportado. Por favor, envie um arquivo Excel.");

        if (!contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            throw new FileNotSupportedException("Tipo de arquivo não suportado. Por favor, envie um arquivo Excel.");
        }
    }
}
