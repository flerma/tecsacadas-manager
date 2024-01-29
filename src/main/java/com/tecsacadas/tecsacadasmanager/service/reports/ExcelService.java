package com.tecsacadas.tecsacadasmanager.service.reports;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public Workbook criarWorkbook() {
        return new XSSFWorkbook();
    }

    public Sheet criarSheet(Workbook workbook, String nomePlanilha, List<Integer> largurasColunas) {
        Sheet sheet = workbook.createSheet(nomePlanilha);
        for (int i = 0; i < largurasColunas.size(); i++) {
            sheet.setColumnWidth(i, largurasColunas.get(i));
        }
        return sheet;
    }

    public Row criarHeaderRow(Sheet sheet, Workbook workbook, List<String> cabecalhos) {
        Row header = sheet.createRow(0);
        CellStyle headerStyle = criarHeaderStyle(workbook);
        for (int i = 0; i < cabecalhos.size(); i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(cabecalhos.get(i));
            headerCell.setCellStyle(headerStyle);
        }
        return header;
    }

    public void adicionarLinha(Sheet sheet, int numeroLinha, List<String> valoresCelula) {
        Row row = sheet.createRow(numeroLinha);
        for (int i = 0; i < valoresCelula.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(valoresCelula.get(i));
        }
    }

    public void salvarArquivo(Workbook workbook, String nomeArquivo) {
        try {
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + nomeArquivo;
            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CellStyle criarHeaderStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);
        return headerStyle;
    }
}
