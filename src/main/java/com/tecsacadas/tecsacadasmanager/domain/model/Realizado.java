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
public class Realizado {

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
    private List<RealizadoMateriais> materiais;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<RealizadoServicos> servicos;

    private String observacao;

    private BigDecimal valorTotal;

    @Data
    @Entity
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RealizadoMateriais {

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
    public static class RealizadoServicos {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Integer quantidade;

        @ManyToOne
        private Servico material;

        private BigDecimal valor;

        private String observacao;
    }

}
