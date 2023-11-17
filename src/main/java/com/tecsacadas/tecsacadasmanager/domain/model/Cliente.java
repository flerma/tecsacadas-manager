package com.tecsacadas.tecsacadasmanager.domain.model;


import com.tecsacadas.tecsacadasmanager.domain.enums.EstadoEnum;
import com.tecsacadas.tecsacadasmanager.dto.ClienteDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Long cpf;

    private String rg;

    private String email;

    private Long telefone;

    private String endereco;

    private String numero;

    private String bairro;

    private String cidade;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;

    private Long cep;

    public ClienteDto toDto() {
        return ClienteDto.builder()
                .id(id)
                .nome(nome)
                .cpf(cpf)
                .rg(rg)
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
