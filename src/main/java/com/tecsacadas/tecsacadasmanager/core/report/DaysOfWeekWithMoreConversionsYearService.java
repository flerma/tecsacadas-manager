package com.tecsacadas.tecsacadasmanager.core.report;

import com.tecsacadas.tecsacadasmanager.data.db.lead.LeadFollowUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DaysOfWeekWithMoreConversionsYearService {

    public static final String FILENAME = "RelatorioDiasSemanaComMaisConversoesAno_%s.xlsx";
    public static final String SHEET_NAME = "Relatório";
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public ByteArrayInputStream generate(Integer year) {

        var workbook = excelService.createWorkbook();
        var columnsWidth = List.of(3000, 6000, 7000);
        var sheet = excelService.createSheet(workbook, SHEET_NAME, columnsWidth);
        var headers = List.of("Data", "Dia da Semana", "Total de Conversões");

        excelService.createHeaderRow(sheet, workbook, headers);

        var conversionsPerYear = leadFollowUpRepository.findDaysOfWeekWithMoreConversionsYear(year);
        var conversionsPerYearLimited = conversionsPerYear.stream().limit(20).toList();
        int i = 1;
        for (var line : conversionsPerYearLimited) {
            var values = List.of(
                    line.getDate().format(dateFormat),
                    line.getDayOfWeek(),
                    line.getConversions().toString()
            );
            excelService.addLine(sheet, i++, values);
        }
        excelService.saveFile(workbook, String.format(FILENAME, year));
        log.info(FILENAME + " gerado com sucesso!", year);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    @SneakyThrows
    public ByteArrayInputStream generate(String identifier, Integer year, Integer month) {

        var reportIdentifier = identifier;
        var workbook = excelService.createWorkbook();
        var columnsWidth = List.of(3000, 6000, 7000);
        var sheet = excelService.createSheet(workbook, SHEET_NAME, columnsWidth);
        var headers = List.of("Data", "Dia da Semana", "Total de Conversões");

        excelService.createHeaderRow(sheet, workbook, headers);

        var conversionsPerYear = leadFollowUpRepository.findDaysOfWeekWithMoreConversionsYear(year);
        var conversionsPerYearLimited = conversionsPerYear.stream().limit(20).toList();
        int i = 1;
        for (var line : conversionsPerYearLimited) {
            var values = List.of(
                    line.getDate().format(dateFormat),
                    line.getDayOfWeek(),
                    line.getConversions().toString()
            );
            excelService.addLine(sheet, i++, values);
        }
        excelService.saveFile(workbook, String.format(FILENAME, year));
        log.info(FILENAME + " gerado com sucesso!", year);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
