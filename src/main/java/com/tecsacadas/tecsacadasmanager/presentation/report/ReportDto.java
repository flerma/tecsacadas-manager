package com.tecsacadas.tecsacadasmanager.presentation.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tecsacadas.tecsacadasmanager.core.report.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;

    private String name;

    private String identifier;

    private String reportName;

    public static ReportDto toDto(Report report) {
        return ReportDto.builder()
                .id(report.getId())
                .source(report.getSource())
                .name(report.getName())
                .identifier(report.getIdentifier())
                .reportName(report.getReportName())
                .build();
    }

    public Report toDomain() {
        return Report.builder()
                .id(id)
                .source(source)
                .name(name)
                .identifier(identifier)
                .reportName(reportName)
                .build();
    }
}

