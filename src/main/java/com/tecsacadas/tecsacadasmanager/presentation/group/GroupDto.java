package com.tecsacadas.tecsacadasmanager.presentation.group;

import com.tecsacadas.tecsacadasmanager.core.group.Group;
import com.tecsacadas.tecsacadasmanager.core.permission.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Set<Permission> permissions;

    public Group toModel() {
        return Group.builder()
                .id(id)
                .name(name)
                .description(description)
                .permissions(permissions)
                .build();
    }
}
