package com.tecsacadas.tecsacadasmanager.controller;

import com.tecsacadas.tecsacadasmanager.dto.ReportDto;
import com.tecsacadas.tecsacadasmanager.service.relatorios.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class RelatorioController {

    private final ReportService reportService;

    @GetMapping
    public List<ReportDto> getAllRelatorios() {
        return reportService.getAllRelatorios();
    }

    @GetMapping("/{id}")
    public ReportDto getRelatorioById(@PathVariable Long id) {
        return reportService.getRelatorioById(id);
    }

    @PostMapping
    public ReportDto createRelatorio(@RequestBody ReportDto reportDto) {
        return reportService.createRelatorio(reportDto);
    }

    @PutMapping("/{id}")
    public ReportDto updateRelatorio(@PathVariable Long id, @RequestBody ReportDto reportDto) {
        return reportService.updateRelatorio(id, reportDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRelatorio(@PathVariable Long id) {
        reportService.deleteRelatorio(id);
    }
}
