package com.barrilito.barrilito.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barrilito.barrilito.model.CategoryEntity;
import com.barrilito.barrilito.repository.I_CategoryRepository;
import com.barrilito.barrilito.serviceInterface.I_CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements I_CategoryService{

    @Autowired
    private ModelMapper modelMapper;
    
    private final I_CategoryRepository categoryRepository;

    @Override
    public CategoryEntity saveCategory(CategoryEntity category) {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new RuntimeException("Could not save category");
        }        
    }

    @Override
    public List<CategoryEntity> getAllCategory() {
        try{
            return categoryRepository.findAll();
        }catch(Exception e){
            throw new UnsupportedOperationException("Unimplemented method 'saveCategory'");
        }
    }

    @Override
    public Optional<CategoryEntity> getCategoryById(Long id) {
        try {
            return categoryRepository.findById(id);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getCategotyById'");
        }        
    }

    @Override
    public CategoryEntity updateCategory(Long id, CategoryEntity newCategory) {
        try{
            return categoryRepository.findById(id).map(existingCategory -> {
                modelMapper.map(newCategory, existingCategory);
                return categoryRepository.save(existingCategory);
            })
            .orElseThrow(() -> new RuntimeException("Id category " + id + " not found"));

        }catch(Exception e){
            throw new RuntimeException("Could not update category");
        }        
    }

    @Override
    public void deleteCategory(Long id) {        
        try {
            categoryRepository.findById(id).map(existingCategory ->{
                existingCategory.setStatus(false);
                return categoryRepository.save(existingCategory);
            })
            .orElseThrow(()-> new RuntimeException("Id category " + id + " not found"));
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'deleteCategory'");
        }        
    }
}
