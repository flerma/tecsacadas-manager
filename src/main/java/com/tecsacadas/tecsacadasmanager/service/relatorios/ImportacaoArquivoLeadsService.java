package com.tecsacadas.tecsacadasmanager.service.relatorios;

import com.tecsacadas.tecsacadasmanager.domain.enums.AcompanhamentoLeadColunas;
import com.tecsacadas.tecsacadasmanager.domain.model.AcompanhamentoLead;
import com.tecsacadas.tecsacadasmanager.repository.AcompanhamentoLeadRepository;
import com.tecsacadas.tecsacadasmanager.service.FileService;
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

import static com.tecsacadas.tecsacadasmanager.domain.enums.AcompanhamentoLeadColunas.LOCAL;
import static com.tecsacadas.tecsacadasmanager.domain.enums.AcompanhamentoLeadColunas.OBSERVACAO;
import static org.springframework.util.StringUtils.hasLength;

@Service
@RequiredArgsConstructor
public class ImportacaoArquivoLeadsService {

    private static final int LINHA_ZERO = 0;
    private static final int PRIMEIRA_PLANILHA = 0;

    @Value("${acompanhamento-leads.path}")
    private String path;

    private final AcompanhamentoLeadRepository acompanhamentoLeadRepository;
    private final FileService fileService;

    public List<AcompanhamentoLead> lerArquivo() throws IOException {
        var fileInputStream = fileService.getArquivo(path);
        var workbook = new XSSFWorkbook(fileInputStream);
        return lerArquivo(workbook);
    }

    public List<AcompanhamentoLead> lerArquivo(InputStream inputStream) throws IOException {
        var workbook = new XSSFWorkbook(inputStream);
        return lerArquivo(workbook);
    }

    public List<AcompanhamentoLead> lerArquivo(XSSFWorkbook workbook) throws IOException {

        acompanhamentoLeadRepository.deleteAll();

        var sheet = workbook.getSheetAt(PRIMEIRA_PLANILHA);
        var acompanhamentoLeadList = new ArrayList<AcompanhamentoLead>();
        var i = LINHA_ZERO;

        for (Row row : sheet) {
            if(i == 0) {
                i++;
                continue;
            }

            AcompanhamentoLead acompanhamentoLead = new AcompanhamentoLead();

            for (Cell cell : row) {
                if (cell.getColumnIndex() > OBSERVACAO.getNumeroColuna())
                    break;

                if (i > LINHA_ZERO
                        && LOCAL.getNumeroColuna() == cell.getColumnIndex()
                        && !hasLength(cell.getStringCellValue()))
                    return acompanhamentoLeadList;

                buidAcompanhamentoLeadEntity(cell, acompanhamentoLead);
            }
            acompanhamentoLeadList.add(acompanhamentoLead);
            i++;
        }
        sheet.getWorkbook().close();
        workbook.close();

        return acompanhamentoLeadList;
    }

    private static void buidAcompanhamentoLeadEntity(Cell cell, AcompanhamentoLead acompanhamentoLead) {

        if (cell.getColumnIndex() == AcompanhamentoLeadColunas.DATA.getNumeroColuna())
            acompanhamentoLead.setDataContato(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        else if (cell.getColumnIndex() == AcompanhamentoLeadColunas.LOCAL.getNumeroColuna())
            acompanhamentoLead.setLocalOrigem(cell.getStringCellValue());
        else if (cell.getColumnIndex() == AcompanhamentoLeadColunas.O_QUE_O_LEAD_PROCUROU.getNumeroColuna())
            acompanhamentoLead.setProcurou(cell.getStringCellValue());
        else if (cell.getColumnIndex() == AcompanhamentoLeadColunas.TIPO_SERVICO_DESEJADO.getNumeroColuna())
            acompanhamentoLead.setTipoServico(cell.getStringCellValue());
        else if (cell.getColumnIndex() == AcompanhamentoLeadColunas.CLIENTE_POTENCIAL.getNumeroColuna())
            acompanhamentoLead.setClientePotencial(cell.getStringCellValue());
        else if (cell.getColumnIndex() == AcompanhamentoLeadColunas.MOTIVO_POR_NAO_SER_POTENCIAL.getNumeroColuna())
            acompanhamentoLead.setMotivoNaoSerPotencial(cell.getStringCellValue());
        else if (cell.getColumnIndex() == AcompanhamentoLeadColunas.FORTMA_DE_CONTATO.getNumeroColuna())
            acompanhamentoLead.setFormaContato(cell.getStringCellValue());
        else if (cell.getColumnIndex() == AcompanhamentoLeadColunas.STATUS.getNumeroColuna())
            acompanhamentoLead.setStatus(cell.getStringCellValue());
        else if (cell.getColumnIndex() == AcompanhamentoLeadColunas.OBSERVACAO.getNumeroColuna())
            acompanhamentoLead.setObservacao(cell.getStringCellValue());
    }
}
