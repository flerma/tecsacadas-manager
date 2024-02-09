package com.tecsacadas.tecsacadasmanager.core.report;

import com.tecsacadas.tecsacadasmanager.data.db.lead.LeadFollowUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class DaysOfWeekWithMoreConversionsMonthService {

    public static final String NOME_PLANILHA = "Relatório";
    public static final String NOME_ARQUIVO = "RelatorioDiasSemanaComMaisConversoesAno_%s_Mes_%s.xlsx";
    private DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void generate(Integer ano, Integer mes) {

        var workbook = excelService.createWorkbook();
        var largurasColunas = List.of(3000, 6000, 7000);
        var sheet = excelService.createSheet(workbook, NOME_PLANILHA, largurasColunas);
        var cabecalhos = List.of("Data", "Dia da Semana", "Total de Conversões");

        excelService.createHeaderRow(sheet, workbook, cabecalhos);

        var conversoesPorMesAno = leadFollowUpRepository.findDaysOfWeekWithMoreConversionsMonth(ano, mes);
        var conversoesPorMesAnoLimitado = conversoesPorMesAno.stream().limit(10).toList();
        int i = 1;
        for (var linha : conversoesPorMesAnoLimitado) {
            var valores = List.of(
                    linha.getDate().format(formatoData),
                    linha.getDayOfWeek(),
                    linha.getConversions().toString()
            );
            excelService.addLine(sheet, i++, valores);
        }

        excelService.saveFile(workbook, String.format(NOME_ARQUIVO, ano, mes));

        log.info(NOME_ARQUIVO + " gerado com sucesso!", ano, mes);
    }
}
