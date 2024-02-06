package com.tecsacadas.tecsacadasmanager.data.db.report;

import com.tecsacadas.tecsacadasmanager.core.report.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReportRepository {

    private final ReportDao reportDao;

    public List<Report> findAll() {
        return reportDao.findAll().stream().map(ReportEntity::toDomain).toList();
    }

    public Optional<Report> findById(Long id) {
        return reportDao.findById(id).map(ReportEntity::toDomain);
    }

    public Report save(Report report) {
        return reportDao.save(ReportEntity.toEntity(report)).toDomain();
    }

    public void deleteById(Long id) {
        reportDao.deleteById(id);
    }
}
