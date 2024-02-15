package com.tecsacadas.tecsacadasmanager.core.report;

import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.SaveExcelFileExceptionException;
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

    public Workbook createWorkbook() {
        return new XSSFWorkbook();
    }

    public Sheet createSheet(Workbook workbook, String sheetsName, List<Integer> columnWidth) {
        Sheet sheet = workbook.createSheet(sheetsName);
        for (int i = 0; i < columnWidth.size(); i++) {
            sheet.setColumnWidth(i, columnWidth.get(i));
        }
        return sheet;
    }

    public Row createHeaderRow(Sheet sheet, Workbook workbook, List<String> headers) {
        Row header = sheet.createRow(0);
        CellStyle headerStyle = createHeaderStyle(workbook);
        for (int i = 0; i < headers.size(); i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(headers.get(i));
            headerCell.setCellStyle(headerStyle);
        }
        return header;
    }

    public void addLine(Sheet sheet, int lineNumber, List<String> cellValues) {
        Row row = sheet.createRow(lineNumber);
        for (int i = 0; i < cellValues.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(cellValues.get(i));
        }
    }

    public void saveFile(Workbook workbook, String filename) {
        try {
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + filename;
            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new SaveExcelFileExceptionException("Erro ao savar o arquivo Excel", e);
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
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
