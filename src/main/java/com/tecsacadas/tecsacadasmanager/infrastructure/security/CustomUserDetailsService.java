package com.tecsacadas.tecsacadasmanager.infrastructure.security;

import com.tecsacadas.tecsacadasmanager.data.db.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService { //} implements UserDetailsService {

    private final UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> usuario = usuarios.findByLogin(username);
//
//        if (!usuario.isPresent()) {
//            throw new UsernameNotFoundException("Usuário não encontrado!");
//        }
//
//        return new UsuarioSistema(usuario.get().getName(), usuario.get().getLogin(), usuario.get().getPassword(), authorities(usuario.get()));
//    }

//    public Collection<GrantedAuthority> authorities(User user) {
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        user.getGroups().forEach(grupo -> addAuthorithies(grupo, authorities));
//        return authorities;
//    }

//    private static void addAuthorithies(Group group, Set<GrantedAuthority> authorities) {
//        group.getPermissions().forEach(permissao -> {
//            authorities.add(new SimpleGrantedAuthority(permissao.getName()));
//        });
//    }
}
