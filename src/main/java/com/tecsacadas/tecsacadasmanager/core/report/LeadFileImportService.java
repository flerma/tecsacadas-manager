package com.tecsacadas.tecsacadasmanager.core.report;

import com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUp;
import com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUpColunas;
import com.tecsacadas.tecsacadasmanager.data.db.lead.LeadFollowUpRepository;
import com.tecsacadas.tecsacadasmanager.infrastructure.util.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUpColunas.LOCAL;
import static com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUpColunas.NOTE;
import static org.springframework.util.StringUtils.hasLength;

@Service
@RequiredArgsConstructor
public class LeadFileImportService {

    private static final int ZERO_LINE = 0;
    private static final int FIRST_SHEET = 0;

    @Value("${acompanhamento-leads.path}")
    private String path;

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final FileService fileService;

    public List<LeadFollowUp> readFile() throws IOException {
        var fileInputStream = fileService.getArquivo(path);
        var workbook = new XSSFWorkbook(fileInputStream);
        return readFile(workbook);
    }

    public List<LeadFollowUp> readFile(InputStream inputStream) throws IOException {
        var workbook = new XSSFWorkbook(inputStream);
        return readFile(workbook);
    }

    public List<LeadFollowUp> readFile(XSSFWorkbook workbook) throws IOException {

        leadFollowUpRepository.deleteAll();

        var sheet = workbook.getSheetAt(FIRST_SHEET);
        var leadFollowUpList = new ArrayList<LeadFollowUp>();
        var i = ZERO_LINE;

        for (Row row : sheet) {
            if(i == 0) {
                i++;
                continue;
            }

            LeadFollowUp leadFollowUp = new LeadFollowUp();

            for (Cell cell : row) {
                if (cell.getColumnIndex() > NOTE.getColumnNumber())
                    break;

                if (i > ZERO_LINE
                        && LOCAL.getColumnNumber() == cell.getColumnIndex()
                        && !hasLength(cell.getStringCellValue()))
                    return leadFollowUpList;

                buidLeadFollowUpEntity(cell, leadFollowUp);
            }
            leadFollowUpList.add(leadFollowUp);
            i++;
        }
        sheet.getWorkbook().close();
        workbook.close();

        return leadFollowUpList;
    }

    private static void buidLeadFollowUpEntity(Cell cell, LeadFollowUp leadFollowUp) {

        if (cell.getColumnIndex() == LeadFollowUpColunas.DATA.getColumnNumber())
            leadFollowUp.setDataContato(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        else if (cell.getColumnIndex() == LeadFollowUpColunas.LOCAL.getColumnNumber())
            leadFollowUp.setLocalOrigem(cell.getStringCellValue());
        else if (cell.getColumnIndex() == LeadFollowUpColunas.O_QUE_O_LEAD_PROCUROU.getColumnNumber())
            leadFollowUp.setProcurou(cell.getStringCellValue());
        else if (cell.getColumnIndex() == LeadFollowUpColunas.TIPO_SERVICO_DESEJADO.getColumnNumber())
            leadFollowUp.setTipoServico(cell.getStringCellValue());
        else if (cell.getColumnIndex() == LeadFollowUpColunas.CLIENTE_POTENCIAL.getColumnNumber())
            leadFollowUp.setClientePotencial(cell.getStringCellValue());
        else if (cell.getColumnIndex() == LeadFollowUpColunas.MOTIVO_POR_NAO_SER_POTENCIAL.getColumnNumber())
            leadFollowUp.setMotivoNaoSerPotencial(cell.getStringCellValue());
        else if (cell.getColumnIndex() == LeadFollowUpColunas.FORTMA_DE_CONTATO.getColumnNumber())
            leadFollowUp.setFormaContato(cell.getStringCellValue());
        else if (cell.getColumnIndex() == LeadFollowUpColunas.STATUS.getColumnNumber())
            leadFollowUp.setStatus(cell.getStringCellValue());
        else if (cell.getColumnIndex() == LeadFollowUpColunas.NOTE.getColumnNumber())
            leadFollowUp.setObservacao(cell.getStringCellValue());
    }
}
