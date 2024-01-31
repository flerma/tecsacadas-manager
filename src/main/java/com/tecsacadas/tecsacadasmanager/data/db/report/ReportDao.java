package com.tecsacadas.tecsacadasmanager.data.db.report;

import com.tecsacadas.tecsacadasmanager.core.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

interface ReportDao extends JpaRepository<Report, Long> {
}
