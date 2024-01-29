package com.tecsacadas.tecsacadasmanager.service.relatorios;

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
public class DiasSemanaComMaisConversoesMesService {

    public static final String NOME_PLANILHA = "Relatório";
    public static final String NOME_ARQUIVO = "RelatorioDiasSemanaComMaisConversoesAno_%s_Mes_%s.xlsx";
    private DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void generate(Integer ano, Integer mes) {

        var workbook = excelService.criarWorkbook();
        var largurasColunas = List.of(3000, 6000, 7000);
        var sheet = excelService.criarSheet(workbook, NOME_PLANILHA, largurasColunas);
        var cabecalhos = List.of("Data", "Dia da Semana", "Total de Conversões");

        excelService.criarHeaderRow(sheet, workbook, cabecalhos);

        var conversoesPorMesAno = leadFollowUpRepository.findDiasSemanaComMaisConversoesMes(ano, mes);
        var conversoesPorMesAnoLimitado = conversoesPorMesAno.stream().limit(10).toList();
        int i = 1;
        for (var linha : conversoesPorMesAnoLimitado) {
            var valores = List.of(
                    linha.getData().format(formatoData),
                    linha.getDiaSemana().toString(),
                    linha.getConversoes().toString()
            );
            excelService.adicionarLinha(sheet, i++, valores);
        }

        excelService.salvarArquivo(workbook, String.format(NOME_ARQUIVO, ano, mes));

        log.info(NOME_ARQUIVO + " gerado com sucesso!", ano, mes);
    }
}
