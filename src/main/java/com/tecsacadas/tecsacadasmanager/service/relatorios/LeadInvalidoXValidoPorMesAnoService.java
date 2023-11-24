package com.tecsacadas.tecsacadasmanager.service.relatorios;

import com.tecsacadas.tecsacadasmanager.repository.AcompanhamentoLeadRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadInvalidoXValidoPorMesAnoService {

    public static final String NOME_ARQUIVO = "LeadsValidosXInvalidosPorAno_%s_Mes_%s.xlsx";
    public static final String NOME_PLANILHA = "Relatório";
    private final AcompanhamentoLeadRepository acompanhamentoLeadRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void gerar(Integer ano, Integer mes) {

        var workbook = excelService.criarWorkbook();
        var largurasColunas = List.of(2000, 2000, 7000, 7000);
        var sheet = excelService.criarSheet(workbook, NOME_PLANILHA, largurasColunas);
        var cabecalhos = List.of("Ano", "Mês", "Leads Válidos", "Leads Inválidos");

        excelService.criarHeaderRow(sheet, workbook, cabecalhos);

        var leadsValidos = acompanhamentoLeadRepository.findLeadsValidos(ano, mes);
        var leadsInvalidos = acompanhamentoLeadRepository.findLeadsInvalidos(ano, mes);

        var valores = List.of(
                ano.toString(),
                mes.toString(),
                leadsValidos.toString(),
                leadsInvalidos.toString()
        );
        excelService.adicionarLinha(sheet, 1, valores);

        excelService.salvarArquivo(workbook, String.format(NOME_ARQUIVO, ano, mes));
    }
}
