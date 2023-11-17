package com.tecsacadas.tecsacadasmanager.security;

import com.tecsacadas.tecsacadasmanager.domain.model.Usuario;
import com.tecsacadas.tecsacadasmanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarios;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService.loadUserByUsername()");
        Optional<Usuario> usuario = usuarios.findByLogin(username);

        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return new UsuarioSistema(usuario.get().getNome(), usuario.get().getLogin(), usuario.get().getSenha(), authorities(usuario.get()));
    }

    public Collection<? extends GrantedAuthority> authorities(Usuario usuario) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        usuario.getGrupos().forEach(grupo -> {
            grupo.getPermissoes().forEach(permissao -> {
                authorities.add(new SimpleGrantedAuthority(permissao.getNome()));
            });
        });

        return authorities;
    }
}
