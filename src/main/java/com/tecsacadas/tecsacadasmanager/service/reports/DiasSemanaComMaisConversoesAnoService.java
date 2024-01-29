package com.tecsacadas.tecsacadasmanager.service.reports;

import com.tecsacadas.tecsacadasmanager.repository.LeadFollowUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiasSemanaComMaisConversoesAnoService {

    public static final String NOME_ARQUIVO = "RelatorioDiasSemanaComMaisConversoesAno_%s.xlsx";
    public static final String NOME_PLANILHA = "Relatório";
    private DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void generate(Integer ano) {

        var workbook = excelService.criarWorkbook();
        var largurasColunas = List.of(3000, 6000, 7000);
        var sheet = excelService.criarSheet(workbook, NOME_PLANILHA, largurasColunas);
        var cabecalhos = List.of("Data", "Dia da Semana", "Total de Conversões");

        excelService.criarHeaderRow(sheet, workbook, cabecalhos);

        var conversoesPorAno = leadFollowUpRepository.findDiasSemanaComMaisConversoesAno(ano);
        var conversoesPorAnoLimitado = conversoesPorAno.stream().limit(20).toList();
        int i = 1;
        for (var linha : conversoesPorAnoLimitado) {
            var valores = List.of(
                    linha.getData().format(formatoData),
                    linha.getDiaSemana().toString(),
                    linha.getConversoes().toString()
            );
            excelService.adicionarLinha(sheet, i++, valores);
        }

        excelService.salvarArquivo(workbook, String.format(NOME_ARQUIVO, ano));

        log.info(NOME_ARQUIVO + " gerado com sucesso!", ano);
    }
}
