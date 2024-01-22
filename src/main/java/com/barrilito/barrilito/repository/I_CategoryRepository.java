package com.barrilito.barrilito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barrilito.barrilito.model.CategoryEntity;

@Repository
public interface I_CategoryRepository extends JpaRepository<CategoryEntity, Long>{
    
}
