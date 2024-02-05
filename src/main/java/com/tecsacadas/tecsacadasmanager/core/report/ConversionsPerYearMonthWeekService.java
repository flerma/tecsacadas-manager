package com.tecsacadas.tecsacadasmanager.core.report;

import com.tecsacadas.tecsacadasmanager.data.db.lead.LeadFollowUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConversionsPerYearMonthWeekService {

    public static final String FILENAME = "RelatorioConversoesPorAnoMesSemana_Ano_%s.xlsx";
    public static final String SHEET_NAME = "Relatório";

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void generate(Integer year) {

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

        excelService.saveFile(workbook, String.format(FILENAME, year));

        log.info(FILENAME + " gerado com sucesso!", year);
    }
}
