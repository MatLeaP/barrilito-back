package com.barrilito.barrilito.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.barrilito.barrilito.model.ProductEntity;

public interface I_ProductService {

    ProductEntity saveProduct(ProductEntity product);
    
    List<ProductEntity> getAllProducts();

    Optional<ProductEntity> getProductById(Long id);

    ProductEntity updateProduct(Long id, ProductEntity product);

    void deleteProduct(Long id);

    
}
