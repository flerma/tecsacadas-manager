package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.client.AddressClient;
import com.tecsacadas.tecsacadasmanager.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressClient client;

    public AddressDto findByCep(String cep) {
        return client.findByCep(cep);
    }
}
