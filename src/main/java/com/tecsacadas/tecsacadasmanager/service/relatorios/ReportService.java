package com.tecsacadas.tecsacadasmanager.service.relatorios;

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

    public List<ReportDto> getAllRelatorios() {
        return relatorioRepository.findAll().stream()
                .map(Report::toDto)
                .toList();
    }

    public ReportDto getRelatorioById(Long id) {
        return relatorioRepository.findById(id)
                .map(Report::toDto)
                .orElseThrow(() -> new RelatorioNotFoundException("Relatorio não encontrado"));
    }

    public ReportDto createRelatorio(ReportDto reportDto) {
        return relatorioRepository.save(reportDto.toModel()).toDto();
    }

    public ReportDto updateRelatorio(Long id, ReportDto reportDto) {
        Report existente = relatorioRepository.findById(id)
                .orElseThrow(() -> new RelatorioNotFoundException("Relatorio não encontrado"));
        existente.setSource(reportDto.getSource());
        existente.setName(reportDto.getName());
        existente.setIdentifier(reportDto.getIdentifier());
        return relatorioRepository.save(existente).toDto();
    }

    public void deleteRelatorio(Long id) {
        relatorioRepository.deleteById(id);
    }
}
