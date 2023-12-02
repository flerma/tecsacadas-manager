package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.domain.model.Usuario;
import com.tecsacadas.tecsacadasmanager.dto.UsuarioDto;
import com.tecsacadas.tecsacadasmanager.exception.BusinessException;
import com.tecsacadas.tecsacadasmanager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Set<Usuario> listarByGrupoId(Long grupoId) {
        return usuarioRepository.findByGrupoId(grupoId);
    }

    public Optional<UsuarioDto> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(Usuario::toDto);
    }

    public Optional<UsuarioDto> buscarPorLogin(String login) {
        return usuarioRepository.findByLogin(login)
                .map(Usuario::toDto);
    }

    public UsuarioDto criar(UsuarioDto usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByLogin(usuario.getLogin());
        if (usuarioExistente.isPresent()) {
            throw new BusinessException("Usuário já existe com o login: " + usuario.getLogin());
        } else {
            return usuarioRepository.save(usuario.toModel()).toDto();
        }
    }

    public UsuarioDto atualizar(Long id, UsuarioDto usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            usuarioAtualizado.setId(id);
            return usuarioRepository.save(usuarioAtualizado.toModel()).toDto();
        } else {
            throw new BusinessException("Usuário não encontrado com o ID: " + id);
        }
    }

    public void excluir(Long id) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new BusinessException("Usuário não encontrado com o ID: " + id);
        }
    }
}
