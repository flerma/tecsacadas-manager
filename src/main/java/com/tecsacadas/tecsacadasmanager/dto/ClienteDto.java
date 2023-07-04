package com.tecsacadas.tecsacadasmanager.dto;

import com.tecsacadas.tecsacadasmanager.domain.enuns.EstadoEnum;
import com.tecsacadas.tecsacadasmanager.domain.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private Long id;

    private String nome;

    private Long cpf;

    private String email;

    private Long telefone;

    private String endereco;

    private String numero;

    private String bairro;

    private String cidade;

    private EstadoEnum estado;

    private Long cep;

    public Cliente toModel() {
        return Cliente.builder()
                .id(id)
                .nome(nome)
                .cpf(cpf)
                .email(email)
                .telefone(telefone)
                .endereco(endereco)
                .numero(numero)
                .bairro(bairro)
                .cidade(cidade)
                .estado(estado)
                .cep(cep)
                .build();
    }
}
