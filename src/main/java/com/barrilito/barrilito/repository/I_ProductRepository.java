package com.barrilito.barrilito.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.barrilito.barrilito.model.ProductEntity;

public interface I_ProductRepository extends JpaRepository<ProductEntity, Long>{
 
    @Query("SELECT p FROM ProductEntity p WHERE LOWER(p.name) LIKE LOWER(concat('%', :name, '%')) AND LENGTH(:name) >= 3 AND p.status = true")
    List<ProductEntity> findByNameContainingIgnoreCase(String name);


    @Query("SELECT p FROM ProductEntity  p where (p.status = true ) ")
    List<ProductEntity> findByStatusTrue ();
    Optional<ProductEntity> findByNameIgnoreCaseAndStatus(String name, boolean status);
    
}
