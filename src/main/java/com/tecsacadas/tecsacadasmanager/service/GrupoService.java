package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.domain.model.Grupo;
import com.tecsacadas.tecsacadasmanager.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoService {

    private final GrupoRepository grupoRepository;

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }
}
