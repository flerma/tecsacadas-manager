package com.tecsacadas.tecsacadasmanager.core.address;

import com.tecsacadas.tecsacadasmanager.data.feign.address.AddressClient;
import com.tecsacadas.tecsacadasmanager.presentation.address.AddressDto;
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
