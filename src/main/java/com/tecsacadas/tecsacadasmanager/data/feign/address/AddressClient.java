package com.tecsacadas.tecsacadasmanager.data.feign.address;

import com.tecsacadas.tecsacadasmanager.presentation.address.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "endereco-client", url = "${cep-api.url}")
public interface AddressClient {

    @GetMapping(path = "/{cep}/json")
    AddressDto findByCep(@PathVariable("cep") String cep);
}
