package com.barrilito.barrilito.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.barrilito.barrilito.model.CategoryEntity;

public interface I_CategoryService {
    
    CategoryEntity saveCategory(CategoryEntity category);

    List<CategoryEntity> getAllCategory();

    Optional<CategoryEntity> getCategoryById(Long id);

    CategoryEntity updateCategory(Long id,  CategoryEntity newCategory);

    void deleteCategory(Long id);
    
}
