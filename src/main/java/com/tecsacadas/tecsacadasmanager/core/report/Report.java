package com.tecsacadas.tecsacadasmanager.core.report;

import com.tecsacadas.tecsacadasmanager.presentation.report.ReportDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
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

    private String reportName;

    public ReportDto toDto() {
        return ReportDto.builder()
                .id(id)
                .source(source)
                .name(name)
                .identifier(identifier)
                .reportName(reportName)
                .build();
    }
}

