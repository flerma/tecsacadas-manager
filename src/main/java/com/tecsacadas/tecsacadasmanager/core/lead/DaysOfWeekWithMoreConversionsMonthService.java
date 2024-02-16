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
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DaysOfWeekWithMoreConversionsMonthService implements LeadFollowUpReportStrategy {

    public static final String SHEET_NAME = "Relatório";
    public static final String FILENAME = "RelatorioDiasSemanaComMaisConversoesAno_%s_Mes_%s.xlsx";
    private DateTimeFormatter formatOfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void generate(Integer year, Integer month) {
        excelService.saveFile(getWorkbook(year, month), String.format(FILENAME, year, month));
        log.info(FILENAME + " gerado com sucesso!", year, month);
    }

    @SneakyThrows
    public ByteArrayInputStream download(Integer year, Integer month) {
        var workbook = getWorkbook(year, month);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    @NotNull
    private Workbook getWorkbook(Integer year, Integer month) {
        var workbook = excelService.createWorkbook();
        var columnsWidth = List.of(3000, 6000, 7000);
        var sheet = excelService.createSheet(workbook, SHEET_NAME, columnsWidth);
        var headers = List.of("Data", "Dia da Semana", "Total de Conversões");

        excelService.createHeaderRow(sheet, workbook, headers);

        var conversionsByMonthYear = leadFollowUpRepository.findDaysOfWeekWithMoreConversionsMonth(year, month);
        var conversionsByMonthYearLimited = conversionsByMonthYear.stream().limit(10).toList();
        int i = 1;
        for (var lines : conversionsByMonthYearLimited) {
            var values = List.of(
                    lines.getDate().format(formatOfDate),
                    lines.getDayOfWeek(),
                    lines.getConversions().toString()
            );
            excelService.addLine(sheet, i++, values);
        }
        return workbook;
    }
}
