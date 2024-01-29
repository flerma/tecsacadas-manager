package com.tecsacadas.tecsacadasmanager.service.relatorios;

import com.tecsacadas.tecsacadasmanager.repository.LeadFollowUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConversoesPorAnoMesService {

    public static final String NOME_ARQUIVO = "RelatorioConversoesPorAnoEMes_Ano_%s.xlsx";
    public static final String NOME_PLANILHA = "Relatório";
    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void generate(Integer ano) {

        var workbook = excelService.criarWorkbook();
        var largurasColunas = List.of(2000, 2000, 7000);
        var sheet = excelService.criarSheet(workbook, NOME_PLANILHA, largurasColunas);
        var cabecalhos = List.of("Ano", "Mês", "Total de Conversões");
        excelService.criarHeaderRow(sheet, workbook, cabecalhos);

        var conversoesPorMesAno = leadFollowUpRepository.findConversoesPorMesAno(ano);
        int i = 1;
        for (var linha : conversoesPorMesAno) {
            var valores = List.of(
                    linha.getAno().toString(),
                    linha.getMes().toString(),
                    linha.getConversoes().toString()
            );
            excelService.adicionarLinha(sheet, i++, valores);
        }
        excelService.salvarArquivo(workbook, String.format(NOME_ARQUIVO, ano));

        log.info(NOME_ARQUIVO + " gerado com sucesso!", ano);
    }
}
