package com.tecsacadas.tecsacadasmanager.service.relatorios;

import com.tecsacadas.tecsacadasmanager.repository.AcompanhamentoLeadRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversoesPorAnoMesSemana {

    public static final String NOME_ARQUIVO = "RelatorioConversoesPorAnoMesSemana_Ano_%s.xlsx";
    public static final String NOME_PLANILHA = "Relatório";

    private final AcompanhamentoLeadRepository acompanhamentoLeadRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public Mono<Void> gerar(Integer ano) {

        return Mono.fromRunnable(() -> {
            var workbook = excelService.criarWorkbook();
            var largurasColunas = List.of(2000, 2000, 3000, 7000);
            var sheet = excelService.criarSheet(workbook, NOME_PLANILHA, largurasColunas);
            var cabecalhos = List.of("Ano", "Mês", "Semana", "Total de Conversões");

            excelService.criarHeaderRow(sheet, workbook, cabecalhos);

            var conversoesPorMesAnoSemana = acompanhamentoLeadRepository.findConversoesPorMesAnoSemana(ano);
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
        });
    }
}
