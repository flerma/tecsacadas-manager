package com.tecsacadas.tecsacadasmanager.dto;

import com.tecsacadas.tecsacadasmanager.domain.model.Group;
import com.tecsacadas.tecsacadasmanager.domain.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
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
