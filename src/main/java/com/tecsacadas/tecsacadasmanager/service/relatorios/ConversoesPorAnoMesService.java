package com.tecsacadas.tecsacadasmanager.service.relatorios;

import com.tecsacadas.tecsacadasmanager.repository.AcompanhamentoLeadRepository;
import lombok.Builder;
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
public class ConversoesPorAnoMesService {

    public static final String NOME_ARQUIVO = "RelatorioConversoesPorAnoEMes.xlsx";
    public static final String NOME_PLANILHA = "Relatório";
    private final AcompanhamentoLeadRepository acompanhamentoLeadRepository;
    private final ExcelService excelService;

    @SneakyThrows
    public void gerar() {

        var workbook = excelService.criarWorkbook();
        var largurasColunas = List.of(2000, 2000, 7000);
        var sheet = excelService.criarSheet(workbook, NOME_PLANILHA, largurasColunas);
        var cabecalhos = List.of("Ano", "Mês", "Total de Conversões");
        excelService.criarHeaderRow(sheet, workbook, cabecalhos);

        var conversoesPorMesAno = acompanhamentoLeadRepository.findConversoesPorMesAno();
        int i = 1;
        for (var linha : conversoesPorMesAno) {
            var valores = List.of(
                    linha.getAno().toString(),
                    linha.getMes().toString(),
                    linha.getConversoes().toString()
            );
            excelService.adicionarLinha(sheet, i++, valores);
        }

        excelService.salvarArquivo(workbook, NOME_ARQUIVO);
    }
}
