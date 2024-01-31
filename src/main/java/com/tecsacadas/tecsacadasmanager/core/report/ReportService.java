package com.tecsacadas.tecsacadasmanager.core.report;

import com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUpService;
import com.tecsacadas.tecsacadasmanager.data.db.report.ReportRepository;
import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.RelatorioNotFoundException;
import com.tecsacadas.tecsacadasmanager.presentation.report.ReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final LeadFollowUpService leadFollowUpService;

    public List<ReportDto> getAllReports() {
        return reportRepository.findAll().stream()
                .map(Report::toDto)
                .toList();
    }

    public ReportDto getReportById(Long id) {
        return reportRepository.findById(id)
                .map(Report::toDto)
                .orElseThrow(() -> new RelatorioNotFoundException("Relatorio não encontrado"));
    }

    public ReportDto createReport(ReportDto reportDto) {
        return reportRepository.save(reportDto.toModel()).toDto();
    }

    public ReportDto updateReport(Long id, ReportDto reportDto) {
        Report existingReport = reportRepository.findById(id)
                .orElseThrow(() -> new RelatorioNotFoundException("Relatorio não encontrado"));
        existingReport.setSource(reportDto.getSource());
        existingReport.setName(reportDto.getName());
        existingReport.setIdentifier(reportDto.getIdentifier());
        return reportRepository.save(existingReport).toDto();
    }

    public void createReport(Long id) {
        reportRepository.deleteById(id);
    }

    public ByteArrayInputStream generateReportByIdentifier(String identifier, Integer year, Integer month) {
        return leadFollowUpService.gerarDownloadDiasSemanaComMaisConversoesAno(year);
    }
}
