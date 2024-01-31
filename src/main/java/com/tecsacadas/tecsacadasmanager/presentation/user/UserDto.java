package com.tecsacadas.tecsacadasmanager.presentation.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tecsacadas.tecsacadasmanager.core.group.Group;
import com.tecsacadas.tecsacadasmanager.core.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

    private Long id;

    private String name;

    private String login;

    private String password;

    private boolean active;

    private Set<Group> groups;

    public User toModel() {
        return User.builder()
                .id(id)
                .name(name)
                .login(login)
                .password(password)
                .active(active)
                .groups(groups)
                .build();
    }
}
