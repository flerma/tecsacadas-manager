package com.tecsacadas.tecsacadasmanager.core.login;

import com.tecsacadas.tecsacadasmanager.core.user.UserService;
import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.NotFoundException;
import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.UnauthorizedException;
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
            throw new NotFoundException("Usuário não encontrado");

        if (notEqual(password, locatedUser.get().getPassword()))
            throw new UnauthorizedException("Senha inválida");

        if (isFalse(locatedUser.get().isActive()))
            throw new UnauthorizedException("Usuário inativo");

        return LoginResponseDto.toDto(locatedUser.get());
    }
}
