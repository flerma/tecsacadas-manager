package com.tecsacadas.tecsacadasmanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tecsacadas.tecsacadasmanager.domain.model.Grupo;
import com.tecsacadas.tecsacadasmanager.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDto implements Serializable {

    private Long id;

    private String nome;

    private String login;

    private String senha;

    private boolean ativo;

    private Set<Grupo> grupos;

    public Usuario toModel() {
        return Usuario.builder()
                .id(id)
                .nome(nome)
                .login(login)
                .senha(senha)
                .ativo(ativo)
                .grupos(grupos)
                .build();
    }
}
