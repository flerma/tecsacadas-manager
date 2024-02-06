package com.tecsacadas.tecsacadasmanager.presentation.group;

import com.tecsacadas.tecsacadasmanager.core.group.Group;
import com.tecsacadas.tecsacadasmanager.data.db.group.GroupEntity;
import com.tecsacadas.tecsacadasmanager.presentation.permission.PermissionDto;
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
public class GroupDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    private List<PermissionDto> permissions;

    public static GroupDto toDto(Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .permissions(group.getPermissions().stream().map(PermissionDto::toDto).toList())
                .build();
    }

    public static Group toDomainStatic(GroupDto group) {
        return Group.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .permissions(group.getPermissions().stream().map(PermissionDto::toDomain).toList())
                .build();
    }

    public GroupEntity toEntity() {
        return GroupEntity.builder()
                .id(id)
                .name(name)
                .description(description)
                .permissions(permissions.stream().map(PermissionDto::toDto).toList())
                .build();
    }



    public static GroupDto toDtoStatic(Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .permissions(group.getPermissions().stream().map(PermissionDto::toDto).toList())
                .build();
    }

    public Group toDomain() {
        return Group.builder()
                .id(id)
                .name(name)
                .description(description)
                .permissions(permissions.stream().map(PermissionDto::toDomain).toList())
                .build();
    }
}
