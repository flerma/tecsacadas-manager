package com.tecsacadas.tecsacadasmanager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Ambiente> ambiente;

    @ManyToOne
    private Cliente cliente;

    private String sistema;

    private String quantidadeFolhas;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<OrcamentoMateriais> materiais;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<OrcamentoServicos> servicos;

    private BigDecimal valorTotal;

    private String observacao;

    @Data
    @Entity
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrcamentoMateriais {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Integer quantidade;

        @ManyToOne
        private Material material;

        private BigDecimal valor;

        private String observacao;
    }

    @Data
    @Entity
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrcamentoServicos {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Integer quantidade;

        @ManyToOne
        private Servico servico;

        private BigDecimal valor;

        private String observacao;
    }

}
