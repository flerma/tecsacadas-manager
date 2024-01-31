package com.tecsacadas.tecsacadasmanager.data.db.report;

import com.tecsacadas.tecsacadasmanager.core.report.Report;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.jar.asm.commons.Remapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReportRepository {

    private final ReportDao reportDao;

    public List<Report> findAll() {
        return reportDao.findAll();
    }

    public Optional<Report> findById(Long id) {
        return reportDao.findById(id);
    }

    public Report save(Report report) {
        return reportDao.save(report);
    }

    public void deleteById(Long id) {
        reportDao.deleteById(id);
    }
}
