package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.domain.enums.AcompanhamentoLeadColunas;
import com.tecsacadas.tecsacadasmanager.domain.model.AcompanhamentoLead;
import com.tecsacadas.tecsacadasmanager.repository.AcompanhamentoLeadRepository;
import com.tecsacadas.tecsacadasmanager.service.relatorios.ConversoesPorAnoMesSemana;
import com.tecsacadas.tecsacadasmanager.service.relatorios.ConversoesPorAnoMesService;
import com.tecsacadas.tecsacadasmanager.service.relatorios.DiasSemanaComMaisConversoesAnoService;
import com.tecsacadas.tecsacadasmanager.service.relatorios.DiasSemanaComMaisConversoesMesService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.tecsacadas.tecsacadasmanager.domain.enums.AcompanhamentoLeadColunas.LOCAL;
import static com.tecsacadas.tecsacadasmanager.domain.enums.AcompanhamentoLeadColunas.OBSERVACAO;
import static org.springframework.util.StringUtils.hasLength;

@Service
@RequiredArgsConstructor
public class AcompanhamentoDeLeadService {

    private static final int LINHA_ZERO = 0;
    private static final int PRIMEIRA_PLANILHA = 0;

    @Value("${acompanhamento-leads.path}")
    private String path;

    private final AcompanhamentoLeadRepository acompanhamentoLeadRepository;
    private final DiasSemanaComMaisConversoesAnoService diasSemanaComMaisConversoesAnoService;
    private final DiasSemanaComMaisConversoesMesService diasSemanaComMaisConversoesMesService;
    private final ConversoesPorAnoMesService conversoesPorAnoMesService;
    private final ConversoesPorAnoMesSemana conversoesPorAnoMesSemana;
    private final FileService fileService;

    @SneakyThrows
    public void importar() {
        var acompanhamentoLeadList = lerArquivo();
        salvar(acompanhamentoLeadList);
    }

    private void salvar(List<AcompanhamentoLead> acompanhamentoLeadList) {
        acompanhamentoLeadRepository.deleteAll();
        acompanhamentoLeadRepository.saveAll(acompanhamentoLeadList);
    }

    public void gerarDiasSemanaComMaisConversoesAno(String ano) {
        diasSemanaComMaisConversoesAnoService.gerar(ano);
    }

    public void gerarDiasSemanaComMaisConversoesMes(String mes) {
        diasSemanaComMaisConversoesMesService.gerar(mes);
    }

    public void gerarConversoesPorAnoMes() {
        conversoesPorAnoMesService.gerar();
    }

    public void gerarConversoesPorAnoMesSemana(String ano) {
        conversoesPorAnoMesSemana.gerar(ano);
    }

    private List<AcompanhamentoLead> lerArquivo() throws IOException {

        acompanhamentoLeadRepository.deleteAll();

        var fileInputStream = fileService.getArquivo(path);

        var workbook = new XSSFWorkbook(fileInputStream);
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
