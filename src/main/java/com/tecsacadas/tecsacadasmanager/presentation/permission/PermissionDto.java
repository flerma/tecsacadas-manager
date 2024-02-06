package com.tecsacadas.tecsacadasmanager.presentation.permission;

import com.tecsacadas.tecsacadasmanager.core.permission.Permission;
import com.tecsacadas.tecsacadasmanager.data.db.permission.PermissionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto implements Serializable {

    private Long id;

    private String name;

    public static PermissionDto toDto(Permission permission) {
        return PermissionDto.builder()
                .id(permission.getId())
                .name(permission.getName())
                .build();
    }

    public PermissionEntity toDto() {
        return PermissionEntity.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static Permission toDomain(PermissionDto permission) {
        return Permission.builder()
                .id(permission.getId())
                .name(permission.getName())
                .build();
    }
}
