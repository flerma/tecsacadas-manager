package com.tecsacadas.tecsacadasmanager.data.db.report;

import com.tecsacadas.tecsacadasmanager.core.report.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report")
class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;

    private String name;

    private String identifier;

    public Report toDomain() {
        return Report.builder()
                .id(id)
                .source(source)
                .name(name)
                .identifier(identifier)
                .build();
    }

    public static ReportEntity toEntity(Report report) {
        return ReportEntity.builder()
                .id(report.getId())
                .source(report.getSource())
                .name(report.getName())
                .identifier(report.getIdentifier())
                .build();
    }
}

