package com.tecsacadas.tecsacadasmanager.service.relatorios;

import com.tecsacadas.tecsacadasmanager.repository.AcompanhamentoLeadRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiasSemanaComMaisConversoesMesService {

    public static final String NOME_PLANILHA = "Relatório";
    public static final String NOME_ARQUIVO = "RelatorioDiasSemanaComMaisConversoesAno_%s_Mes_%s.xlsx";
    private DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final AcompanhamentoLeadRepository acompanhamentoLeadRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void gerar(Integer ano, Integer mes) {

        var workbook = excelService.criarWorkbook();
        var largurasColunas = List.of(3000, 6000, 7000);
        var sheet = excelService.criarSheet(workbook, NOME_PLANILHA, largurasColunas);
        var cabecalhos = List.of("Data", "Dia da Semana", "Total de Conversões");

        excelService.criarHeaderRow(sheet, workbook, cabecalhos);

        var conversoesPorMesAno = acompanhamentoLeadRepository.findDiasSemanaComMaisConversoesMes(ano, mes);
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
    }
}
