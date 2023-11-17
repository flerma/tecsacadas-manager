package com.tecsacadas.tecsacadasmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
@RequiredArgsConstructor
public class FileService {

    public FileInputStream getArquivo(String path) throws FileNotFoundException {
        var file = ResourceUtils.getFile(path);
        return new FileInputStream(file);
    }
}
