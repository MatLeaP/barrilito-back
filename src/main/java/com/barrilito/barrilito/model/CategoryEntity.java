package com.barrilito.barrilito.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_category;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean status;

}
