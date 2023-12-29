package com.tecsacadas.tecsacadasmanager.client;

import com.tecsacadas.tecsacadasmanager.dto.EnderecoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "endereco-client", url = "${cep-api.url}")
public interface EnderecoClient {

    @GetMapping(path = "/{cep}/json")
    EnderecoDto buscarPorCep(@PathVariable("cep") String cep);
}
