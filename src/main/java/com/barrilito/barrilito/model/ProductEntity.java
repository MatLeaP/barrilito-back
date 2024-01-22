package com.barrilito.barrilito.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    @Column
    @NotBlank(message = "Description is required")
    private String description;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 200)
    private String img;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "boolean default true")
    private Boolean status;

    @Column(nullable = false)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private CategoryEntity category;
    
}
