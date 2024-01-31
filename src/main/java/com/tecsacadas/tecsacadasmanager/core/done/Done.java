package com.tecsacadas.tecsacadasmanager.core.done;

import com.tecsacadas.tecsacadasmanager.core.customer.Customer;
import com.tecsacadas.tecsacadasmanager.core.material.Material;
import com.tecsacadas.tecsacadasmanager.core.room.Room;
import com.tecsacadas.tecsacadasmanager.core.service.Service;
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
public class Done {

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
    private List<DoneMaterials> materials;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<DoneServices> services;

    private String note;

    private BigDecimal totalPrice;

    @Data
    @Entity
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DoneMaterials {

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
    public static class DoneServices {

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
