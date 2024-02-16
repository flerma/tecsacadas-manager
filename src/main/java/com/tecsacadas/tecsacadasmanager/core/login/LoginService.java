package com.tecsacadas.tecsacadasmanager.core.login;

import com.tecsacadas.tecsacadasmanager.core.user.UserService;
import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.InactiveUserException;
import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.InvalidUserPasswordException;
import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.UserNotFoundException;
import com.tecsacadas.tecsacadasmanager.presentation.login.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.ObjectUtils.notEqual;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;

    public LoginResponseDto authenticate(String user, String password) {
        var locatedUser = userService.buscarPorLogin(user);

        if (locatedUser.isEmpty())
            throw new UserNotFoundException("Usuário não encontrado");

        if (notEqual(password, locatedUser.get().getPassword()))
            throw new InvalidUserPasswordException("Senha inválida");

        if (isFalse(locatedUser.get().isActive()))
            throw new InactiveUserException("Usuário inativo");

        return LoginResponseDto.toDto(locatedUser.get());
    }
}
