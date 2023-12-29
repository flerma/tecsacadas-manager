package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.client.EnderecoClient;
import com.tecsacadas.tecsacadasmanager.dto.EnderecoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoClient client;

    public EnderecoDto buscarPorCep(String cep) {
        return client.buscarPorCep(cep);
    }
}
