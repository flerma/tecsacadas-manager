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
public class ConversionsPerYearMonthWeekService implements LeadFollowUpReportStrategy {

    public static final String FILENAME = "RelatorioConversoesPorAnoMesSemana_Ano_%s.xlsx";
    public static final String SHEET_NAME = "Relatório";

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void generate(Integer year) {
        var filename = String.format(FILENAME, year);
        excelService.saveFile(getWorkbook(year), filename);
        log.info(filename + " gerado com sucesso!");
    }

    @Override
    @SneakyThrows
    public ByteArrayInputStream download(Integer year) {
        var workbook = getWorkbook(year);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    @NotNull
    private Workbook getWorkbook(Integer year) {
        var workbook = excelService.createWorkbook();
        var columnsWidth = List.of(2000, 2000, 3000, 7000);
        var sheet = excelService.createSheet(workbook, SHEET_NAME, columnsWidth);
        var headers = List.of("Ano", "Mês", "Semana", "Total de Conversões");

        excelService.createHeaderRow(sheet, workbook, headers);

        var conversionsPerYearMonthWeek = leadFollowUpRepository.findConversionsPerYearMonthWeek(year);
        int i = 1;
        for (var line : conversionsPerYearMonthWeek) {
            var values = List.of(
                    line.getYear().toString(),
                    line.getMonth().toString(),
                    line.getWeek().toString(),
                    line.getConversions().toString()
            );
            excelService.addLine(sheet, i++, values);
        }
        return workbook;
    }
}
