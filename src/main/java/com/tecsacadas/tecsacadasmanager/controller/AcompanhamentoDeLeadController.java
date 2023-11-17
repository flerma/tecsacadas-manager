package com.tecsacadas.tecsacadasmanager.controller;

import com.tecsacadas.tecsacadasmanager.service.AcompanhamentoDeLeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acompanhamento-leads")
@RequiredArgsConstructor
public class AcompanhamentoDeLeadController {

    private final AcompanhamentoDeLeadService acompanhamentoLeadsService;

    @GetMapping("/importar")
    public ResponseEntity<Void> importar() {
        acompanhamentoLeadsService.importar();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/conversoes-por-ano-mes")
    public ResponseEntity<Void> gerarConversoesPorAnoMes() {
        acompanhamentoLeadsService.gerarConversoesPorAnoMes();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dias-semana-mais-conversoes-mes/{mes}")
    public ResponseEntity<Void> gerarDiasSemanaComMaisConversoesMes(@PathVariable String mes) {
        acompanhamentoLeadsService.gerarDiasSemanaComMaisConversoesMes(mes);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dias-semana-mais-conversoes-ano/{ano}")
    public ResponseEntity<Void> gerarDiasSemanaComMaisConversoesAno(@PathVariable String ano) {
        acompanhamentoLeadsService.gerarDiasSemanaComMaisConversoesAno(ano);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/conversoes-por-ano-mes-semana/{ano}")
    public ResponseEntity<Void> gerarConversoesPorAnoMesSemana(@PathVariable String ano) {
        acompanhamentoLeadsService.gerarConversoesPorAnoMesSemana(ano);
        return ResponseEntity.ok().build();
    }
}
