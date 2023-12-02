package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UsuarioService usuarioService;

    public boolean autenticar(String usuario, String senha) {
        var usuarioLocalizado = usuarioService.buscarPorLogin(usuario);

        if (!usuarioLocalizado.isPresent())
            throw new RuntimeException("Usuário não encontrado");

        return usuario.equals(usuarioLocalizado.get().getLogin())
               && senha.equals(usuarioLocalizado.get().getSenha());
    }
}
