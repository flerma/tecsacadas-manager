package com.tecsacadas.tecsacadasmanager.dto;

import com.tecsacadas.tecsacadasmanager.domain.enuns.EstadoEnum;
import com.tecsacadas.tecsacadasmanager.domain.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "CPF é obrigatório")
    private Long cpf;

    private String rg;

    @NotBlank(message = "Email é obrigatório")
    @Email
    private String email;

    @NotNull(message = "Telefone é obrigatório")
    private Long telefone;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatório")
    private String cidade;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;

    @NotNull(message = "CEP é obrigatório")
    private Long cep;

    public Cliente toModel() {
        return Cliente.builder()
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
