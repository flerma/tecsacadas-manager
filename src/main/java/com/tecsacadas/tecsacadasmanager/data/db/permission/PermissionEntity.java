package com.tecsacadas.tecsacadasmanager.data.db.permission;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tecsacadas.tecsacadasmanager.core.permission.Permission;
import com.tecsacadas.tecsacadasmanager.presentation.permission.PermissionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "permission")
public class PermissionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Permission toDomain() {
        return Permission.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static PermissionEntity toEntity(Permission permission) {
        return PermissionEntity.builder()
                .id(permission.getId())
                .name(permission.getName())
                .build();
    }
}
