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

@Service
@RequiredArgsConstructor
public class LeadInvalidoXValidoPorMesAnoService {

    public static final String NOME_ARQUIVO = "LeadsValidosXInvalidosPorAnoMes.xlsx";
    public static final String NOME_PLANILHA = "Relatório";
    private final AcompanhamentoLeadRepository acompanhamentoLeadRepository;

    @SneakyThrows
    public void gerar(Integer ano, Integer mes) {

        var leadsValidos = acompanhamentoLeadRepository.findLeadsValidos(ano, mes);
        var leadsInvalidos = acompanhamentoLeadRepository.findLeadsInvalidos(ano, mes);

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet(NOME_PLANILHA);
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 2000);
        sheet.setColumnWidth(2, 7000);
        sheet.setColumnWidth(3, 7000);

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
        headerCell.setCellValue("Ano");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Mes");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Leads Válidos");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Leads Inválidos");
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        Row row = sheet.createRow(1);
        Cell cell = row.createCell(0);
        cell.setCellValue(ano);
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue(mes);
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue(leadsValidos);
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue(leadsInvalidos);
        cell.setCellStyle(style);

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + NOME_ARQUIVO;

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }
}
