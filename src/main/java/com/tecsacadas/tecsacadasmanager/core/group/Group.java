package com.tecsacadas.tecsacadasmanager.core.group;

import com.tecsacadas.tecsacadasmanager.core.permission.Permission;
import com.tecsacadas.tecsacadasmanager.data.db.group.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group implements Serializable {

    private Long id;

    private String name;

    private String description;

    private List<Permission> permissions;

    public static Group toDomain(GroupEntity groupEntity) {
        return Group.builder()
                .id(groupEntity.getId())
                .name(groupEntity.getName())
                .description(groupEntity.getDescription())
                .permissions(groupEntity.getPermissions().stream().map(Permission::toDomain).toList())
                .build();
    }
}
