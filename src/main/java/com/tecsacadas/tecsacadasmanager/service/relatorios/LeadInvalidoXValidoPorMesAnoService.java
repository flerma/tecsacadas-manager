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
public class LeadInvalidoXValidoPorMesAnoService {

    public static final String NOME_ARQUIVO = "LeadsValidosXInvalidosPorAno_%s_Mes_%s.xlsx";
    public static final String NOME_PLANILHA = "Relatório";
    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void generate(Integer ano, Integer mes) {

        var workbook = excelService.criarWorkbook();
        var largurasColunas = List.of(2000, 2000, 7000, 7000);
        var sheet = excelService.criarSheet(workbook, NOME_PLANILHA, largurasColunas);
        var cabecalhos = List.of("Ano", "Mês", "Leads Válidos", "Leads Inválidos");

        excelService.criarHeaderRow(sheet, workbook, cabecalhos);

        var leadsValidos = leadFollowUpRepository.findLeadsValidos(ano, mes);
        var leadsInvalidos = leadFollowUpRepository.findLeadsInvalidos(ano, mes);

        var valores = List.of(
                ano.toString(),
                mes.toString(),
                leadsValidos.toString(),
                leadsInvalidos.toString()
        );
        excelService.adicionarLinha(sheet, 1, valores);

        excelService.salvarArquivo(workbook, String.format(NOME_ARQUIVO, ano, mes));

        log.info(NOME_ARQUIVO + " gerado com sucesso!", ano, mes);
    }
}
