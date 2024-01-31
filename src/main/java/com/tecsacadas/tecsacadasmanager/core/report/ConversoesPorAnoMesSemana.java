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
public class ConversoesPorAnoMesSemana {

    public static final String NOME_ARQUIVO = "RelatorioConversoesPorAnoMesSemana_Ano_%s.xlsx";
    public static final String NOME_PLANILHA = "Relatório";

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void generate(Integer ano) {

        var workbook = excelService.criarWorkbook();
        var largurasColunas = List.of(2000, 2000, 3000, 7000);
        var sheet = excelService.criarSheet(workbook, NOME_PLANILHA, largurasColunas);
        var cabecalhos = List.of("Ano", "Mês", "Semana", "Total de Conversões");

        excelService.criarHeaderRow(sheet, workbook, cabecalhos);

        var conversoesPorMesAnoSemana = leadFollowUpRepository.findConversoesPorMesAnoSemana(ano);
        int i = 1;
        for (var linha : conversoesPorMesAnoSemana) {
            var valores = List.of(
                    linha.getAno().toString(),
                    linha.getMes().toString(),
                    linha.getSemana().toString(),
                    linha.getConversoes().toString()
            );
            excelService.adicionarLinha(sheet, i++, valores);
        }

        excelService.salvarArquivo(workbook, String.format(NOME_ARQUIVO, ano));

        log.info(NOME_ARQUIVO + " gerado com sucesso!", ano);
    }
}
