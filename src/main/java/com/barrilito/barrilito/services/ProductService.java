package com.barrilito.barrilito.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barrilito.barrilito.model.CategoryEntity;
import com.barrilito.barrilito.model.ProductEntity;
import com.barrilito.barrilito.repository.I_ProductRepository;
import com.barrilito.barrilito.serviceInterface.I_ProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements I_ProductService{

    @Autowired
    ModelMapper modelMapper;
    
    
    private final CategoryService categoryService;

    
    private final I_ProductRepository productRepository;

    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        try {
            ProductEntity productEntity = product;

            // Update the product status
            productEntity.setStatus(true);
            // Guarda el producto
            Optional<CategoryEntity> category = categoryService.getCategoryById(product.getCategory().getId_category());
            productEntity.setCategory(category.get());
            return productRepository.save(productEntity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save product with category. Reason: " + e.getMessage());
        }
        
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'"); 
        }
        
    }

    @Override
    public Optional<ProductEntity> getProductById(Long id) {
        try {
            if(id != null){
                return productRepository.findById(id);
            }else{
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error fetching product by ID", e);
        }
        
    }

    @Override
    public ProductEntity updateProduct(Long id ,ProductEntity product) {
        try {
            return productRepository.findById(id).map(existingProduct ->{
                modelMapper.map(product, existingProduct);
                return productRepository.save(existingProduct);
            })
            .orElseThrow(() -> new RuntimeException("Id product " + id + " not found"));
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
        }
        
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            productRepository.findById(id).map(existingProduct ->{
                existingProduct.setStatus(false);
                return productRepository.save(existingProduct);
            })
            .orElseThrow(()-> new RuntimeException("Id product " + id + " not found"));
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
        }
        
    }
    

}
