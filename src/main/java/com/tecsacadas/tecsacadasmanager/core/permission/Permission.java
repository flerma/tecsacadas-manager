package com.tecsacadas.tecsacadasmanager.core.permission;

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
public class Permission implements Serializable {

    private Long id;

    private String name;

    public static Permission toDomain(PermissionEntity permissionEntity) {
        return Permission.builder()
                .id(permissionEntity.getId())
                .name(permissionEntity.getName())
                .build();
    }
}
