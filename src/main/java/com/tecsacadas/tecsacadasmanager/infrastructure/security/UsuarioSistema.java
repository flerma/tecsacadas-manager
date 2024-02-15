package com.tecsacadas.tecsacadasmanager.infrastructure.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;
import java.util.Collection;

@Getter
@EqualsAndHashCode(callSuper = true)
public class UsuarioSistema extends User {

    @Serial
    private static final long serialVersionUID = -4018702906282585536L;

    private final String nome;

    public UsuarioSistema(String nome, String username, String password, Collection<GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.nome = nome;
    }
}
