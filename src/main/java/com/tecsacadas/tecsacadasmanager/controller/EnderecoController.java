package com.tecsacadas.tecsacadasmanager.controller;

import com.tecsacadas.tecsacadasmanager.dto.EnderecoDto;
import com.tecsacadas.tecsacadasmanager.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("/cep")
    public ResponseEntity<EnderecoDto> buscarPorCep(@RequestParam String cep) {
        return ResponseEntity.ok(enderecoService.buscarPorCep(cep));
    }
}
