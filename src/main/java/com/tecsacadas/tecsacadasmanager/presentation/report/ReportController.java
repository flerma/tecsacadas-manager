package com.tecsacadas.tecsacadasmanager.presentation.report;

import com.tecsacadas.tecsacadasmanager.core.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
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

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> getReportByIdentifier(
            @Valid @RequestParam("identifier") @NotNull(message = "Identificador do relatorio deve ser informado") String identifier,
            @Valid @RequestParam("year") @NotNull(message = "Ano deve ser informado") Integer year,
            @RequestParam(value = "month", required = false) Integer month) {

        ByteArrayInputStream in = reportService.generateReportByIdentifier(identifier, year, month);
        var resource = new InputStreamResource(in);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(resource);
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
