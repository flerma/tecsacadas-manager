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
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiasSemanaComMaisConversoesAnoService {

    public static final String NOME_ARQUIVO = "RelatorioDiasSemanaComMaisConversoesAno_%s.xlsx";
    public static final String NOME_PLANILHA = "Relatório";
    private DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final AcompanhamentoLeadRepository acompanhamentoLeadRepository;

    @SneakyThrows
    public void gerar(Integer ano) {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet(NOME_PLANILHA);
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 7000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Data");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Dia da Semana");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Total de Conversões");
        headerCell.setCellStyle(headerStyle);

        var conversoesPorMesAno = acompanhamentoLeadRepository.findDiasSemanaComMaisConversoesAno(ano);
        var conversoesPorMesAnoLimitado = conversoesPorMesAno.stream().limit(10).toList();

        var i = 0;
        for (var diasSemanaComMaisConversoesMes : conversoesPorMesAnoLimitado) {

            CellStyle style = workbook.createCellStyle();
            style.setWrapText(true);

            Row row = sheet.createRow(++i);
            Cell cell = row.createCell(0);
            cell.setCellValue(diasSemanaComMaisConversoesMes.getData().format(formatoData));
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(diasSemanaComMaisConversoesMes.getDiaSemana());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(diasSemanaComMaisConversoesMes.getConversoes());
            cell.setCellStyle(style);
        }

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + String.format(NOME_ARQUIVO, ano);

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }
}
