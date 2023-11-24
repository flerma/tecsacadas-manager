package com.tecsacadas.tecsacadasmanager.security;

import com.tecsacadas.tecsacadasmanager.domain.model.Grupo;
import com.tecsacadas.tecsacadasmanager.domain.model.Usuario;
import com.tecsacadas.tecsacadasmanager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarios;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarios.findByLogin(username);

        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return new UsuarioSistema(usuario.get().getNome(), usuario.get().getLogin(), usuario.get().getSenha(), authorities(usuario.get()));
    }

    public Collection<GrantedAuthority> authorities(Usuario usuario) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        usuario.getGrupos().forEach(grupo -> addAuthorithies(grupo, authorities));
        return authorities;
    }

    private static void addAuthorithies(Grupo grupo, Set<GrantedAuthority> authorities) {
        grupo.getPermissoes().forEach(permissao -> {
            authorities.add(new SimpleGrantedAuthority(permissao.getNome()));
        });
    }
}
