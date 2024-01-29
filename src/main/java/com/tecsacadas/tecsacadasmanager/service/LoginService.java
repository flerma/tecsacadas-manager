package com.tecsacadas.tecsacadasmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;

    public boolean authenticate(String user, String password) {
        var locatedUser = userService.buscarPorLogin(user);

        if (locatedUser.isEmpty())
            throw new RuntimeException("Usuário não encontrado");

        return user.equals(locatedUser.get().getLogin())
               && password.equals(locatedUser.get().getPassword());
    }
}
