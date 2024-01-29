package com.tecsacadas.tecsacadasmanager.controller;

import com.tecsacadas.tecsacadasmanager.dto.AddressDto;
import com.tecsacadas.tecsacadasmanager.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/cep")
    public ResponseEntity<AddressDto> findByCep(@RequestParam String cep) {
        return ResponseEntity.ok(addressService.findByCep(cep));
    }
}
