package com.tecsacadas.tecsacadasmanager.domain.model;

import com.tecsacadas.tecsacadasmanager.dto.ReportDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;

    private String name;

    private String identifier;

    public ReportDto toDto() {
        return ReportDto.builder()
                .id(id)
                .source(source)
                .name(name)
                .identifier(identifier)
                .build();
    }
}

