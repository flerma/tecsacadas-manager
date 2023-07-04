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
public class OrdemServico {

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
    private List<OrdemServicoMateriais> materiais;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<OrdemServicoServicos> servicos;

    private String observacao;

    @Data
    @Entity
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrdemServicoMateriais {

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
    public static class OrdemServicoServicos {

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
