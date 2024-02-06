package com.tecsacadas.tecsacadasmanager.presentation.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tecsacadas.tecsacadasmanager.core.user.User;
import com.tecsacadas.tecsacadasmanager.data.db.group.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
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

    private List<GroupEntity> groups;

    public static UserDto toDto(User user) {
        return UserDto.builder()
        		.id(user.getId())
        		.name(user.getName())
        		.login(user.getLogin())
        		.password(user.getPassword())
        		.active(user.isActive())
        		.build();
    }

    public User toDomain() {
        return User.builder()
        		.id(id)
        		.name(name)
        		.login(login)
        		.password(password)
        		.active(active)
        		.build();
    }
}
