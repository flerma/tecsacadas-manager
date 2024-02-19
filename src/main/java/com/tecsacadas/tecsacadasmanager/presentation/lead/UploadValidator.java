package com.tecsacadas.tecsacadasmanager.presentation.lead;

import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.BusinessException;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
public class UploadValidator {

    public static void validate(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType == null)
            throw new BusinessException("Tipo de arquivo não suportado. Por favor, envie um arquivo Excel.");

        if (!contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            throw new BusinessException("Tipo de arquivo não suportado. Por favor, envie um arquivo Excel.");
        }
    }
}
