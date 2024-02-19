package com.tecsacadas.tecsacadasmanager.data.db.report;

import com.tecsacadas.tecsacadasmanager.core.report.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {

    private final ReportDao reportDao;

    @Override
    public List<Report> findAll() {
        return reportDao.findAll().stream().map(ReportEntity::toDomain).toList();
    }

    @Override
    public Optional<Report> findById(Long id) {
        return reportDao.findById(id).map(ReportEntity::toDomain);
    }

    @Override
    public Report save(Report report) {
        return reportDao.save(ReportEntity.toEntity(report)).toDomain();
    }

    @Override
    public void deleteById(Long id) {
        reportDao.deleteById(id);
    }
}
