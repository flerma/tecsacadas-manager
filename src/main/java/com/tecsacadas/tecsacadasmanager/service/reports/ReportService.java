package com.tecsacadas.tecsacadasmanager.service.reports;

import com.tecsacadas.tecsacadasmanager.domain.model.Report;
import com.tecsacadas.tecsacadasmanager.dto.ReportDto;
import com.tecsacadas.tecsacadasmanager.exception.RelatorioNotFoundException;
import com.tecsacadas.tecsacadasmanager.repository.RelatorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final RelatorioRepository relatorioRepository;

    public List<ReportDto> getAllReports() {
        return relatorioRepository.findAll().stream()
                .map(Report::toDto)
                .toList();
    }

    public ReportDto getReportById(Long id) {
        return relatorioRepository.findById(id)
                .map(Report::toDto)
                .orElseThrow(() -> new RelatorioNotFoundException("Relatorio não encontrado"));
    }

    public ReportDto createReport(ReportDto reportDto) {
        return relatorioRepository.save(reportDto.toModel()).toDto();
    }

    public ReportDto updateReport(Long id, ReportDto reportDto) {
        Report existingReport = relatorioRepository.findById(id)
                .orElseThrow(() -> new RelatorioNotFoundException("Relatorio não encontrado"));
        existingReport.setSource(reportDto.getSource());
        existingReport.setName(reportDto.getName());
        existingReport.setIdentifier(reportDto.getIdentifier());
        return relatorioRepository.save(existingReport).toDto();
    }

    public void createReport(Long id) {
        relatorioRepository.deleteById(id);
    }
}
