package com.tecsacadas.tecsacadasmanager.client;

import com.tecsacadas.tecsacadasmanager.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "endereco-client", url = "${cep-api.url}")
public interface AddressClient {

    @GetMapping(path = "/{cep}/json")
    AddressDto findByCep(@PathVariable("cep") String cep);
}
