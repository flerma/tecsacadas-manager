package com.tecsacadas.tecsacadasmanager.presentation.login;

import com.tecsacadas.tecsacadasmanager.core.login.LoginService;
import com.tecsacadas.tecsacadasmanager.presentation.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseDto> autenticar(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(loginService.authenticate(loginDto.getLogin(), loginDto.getPassword()));
    }
}
