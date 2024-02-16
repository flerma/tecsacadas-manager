package com.tecsacadas.tecsacadasmanager.core.lead;

import com.tecsacadas.tecsacadasmanager.core.report.ExcelService;
import com.tecsacadas.tecsacadasmanager.data.db.lead.LeadFollowUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidXInvalidLeadsPerYearMonthService implements LeadFollowUpReportStrategy {

    public static final String FILENAME = "LeadsValidosXInvalidosPorAno_%s_Mes_%s.xlsx";
    public static final String SHEET_NAME = "Relatório";
    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public ByteArrayInputStream download(Integer year, Integer month) {
        var workbook = getWorkbook(year, month);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    @SneakyThrows
    public void generate(Integer year, Integer month) {
        excelService.saveFile(getWorkbook(year, month), String.format(FILENAME, year, month));
        log.info(FILENAME + " gerado com sucesso!", year, month);
    }

    @NotNull
    private Workbook getWorkbook(Integer year, Integer month) {
        var workbook = excelService.createWorkbook();
        var columnsWidth = List.of(2000, 2000, 7000, 7000);
        var sheet = excelService.createSheet(workbook, SHEET_NAME, columnsWidth);
        var headers = List.of("Ano", "Mês", "Leads Válidos", "Leads Inválidos");

        excelService.createHeaderRow(sheet, workbook, headers);

        var validLeads = leadFollowUpRepository.findValidLeads(year, month);
        var invalidLeads = leadFollowUpRepository.findInvalidLeads(year, month);

        var values = List.of(
                year.toString(),
                month.toString(),
                validLeads.toString(),
                invalidLeads.toString()
        );
        excelService.addLine(sheet, 1, values);
        return workbook;
    }
}
