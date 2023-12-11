package com.tecsacadas.tecsacadasmanager.controller;

import com.tecsacadas.tecsacadasmanager.exception.FileNotSupportedException;
import com.tecsacadas.tecsacadasmanager.service.AcompanhamentoDeLeadService;
import com.tecsacadas.tecsacadasmanager.validator.UploadValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/acompanhamento-leads")
@CrossOrigin
public class AcompanhamentoDeLeadController {

    private final AcompanhamentoDeLeadService acompanhamentoLeadsService;
    private final UploadValidator uploadValidator;

    @GetMapping("/importar")
    public ResponseEntity<Void> importar() {
        acompanhamentoLeadsService.importar();
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<Void> upload(@RequestPart("file") MultipartFile file) {
        uploadValidator.validate(file);
        acompanhamentoLeadsService.upload(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/todos-relatorios/{ano}/{mes}")
    public ResponseEntity<Void> gerarTodosRelatorios(@PathVariable Integer ano, @PathVariable Integer mes) {
        acompanhamentoLeadsService.gerarTodosRelatorios(ano, mes);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/conversoes-por-ano-mes/{ano}")
    public ResponseEntity<Void> gerarConversoesPorAnoMes(@PathVariable Integer ano) {
        acompanhamentoLeadsService.gerarConversoesPorAnoMes(ano);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dias-semana-mais-conversoes-mes/{ano}/{mes}")
    public ResponseEntity<Void> gerarDiasSemanaComMaisConversoesMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        acompanhamentoLeadsService.gerarDiasSemanaComMaisConversoesMes(ano, mes);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dias-semana-mais-conversoes-ano/{ano}")
    public ResponseEntity<Void> gerarDiasSemanaComMaisConversoesAno(@PathVariable Integer ano) {
        acompanhamentoLeadsService.gerarDiasSemanaComMaisConversoesAno(ano);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/conversoes-por-ano-mes-semana/{ano}")
    public ResponseEntity<Void> gerarConversoesPorAnoMesSemana(@PathVariable Integer ano) {
        acompanhamentoLeadsService.gerarConversoesPorAnoMesSemana(ano);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/leads-invalidos-e-validos-por-ano-mes/{ano}/{mes}")
    public ResponseEntity<Void> gerarLeadsValidosXInvalidosPorAnoMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        acompanhamentoLeadsService.gerarLeadsValidosXInvalidosPorAnoMes(ano, mes);
        return ResponseEntity.ok().build();
    }
}
