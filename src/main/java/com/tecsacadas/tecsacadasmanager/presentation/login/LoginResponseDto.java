package com.tecsacadas.tecsacadasmanager.presentation.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tecsacadas.tecsacadasmanager.presentation.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseDto {

    private String name;

    private String login;

    public static LoginResponseDto toDto(UserDto userDto) {
        return LoginResponseDto.builder()
            .name(userDto.getName())
            .login(userDto.getLogin())
            .build();
    }
}
