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
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Room> room;

    @ManyToOne
    private Customer customer;

    private String system;

    private String quantityGlassSheets;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<BudgetMaterials> materials;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<BudgetServices> services;

    private BigDecimal totalPrice;

    private String note;

    @Data
    @Entity
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BudgetMaterials {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Integer quantity;

        @ManyToOne
        private Material material;

        private BigDecimal price;

        private String note;
    }

    @Data
    @Entity
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BudgetServices {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Integer quantity;

        @ManyToOne
        private Service service;

        private BigDecimal price;

        private String note;
    }

}
