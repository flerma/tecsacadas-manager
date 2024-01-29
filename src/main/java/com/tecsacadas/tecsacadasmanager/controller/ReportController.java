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
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public List<ReportDto> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ReportDto getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @PostMapping
    public ReportDto createReport(@RequestBody ReportDto reportDto) {
        return reportService.createReport(reportDto);
    }

    @PutMapping("/{id}")
    public ReportDto updateReport(@PathVariable Long id, @RequestBody ReportDto reportDto) {
        return reportService.updateReport(id, reportDto);
    }

    @DeleteMapping("/{id}")
    public void createReport(@PathVariable Long id) {
        reportService.createReport(id);
    }
}
