package com.barrilito.barrilito.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.barrilito.barrilito.model.ProductEntity;
import com.barrilito.barrilito.serviceInterface.I_ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
//@CrossOrigin(origins ="http://127.0.0.1:5173/")
public class ProductController {

    @Autowired
    I_ProductService service;

    @GetMapping("/list")
        public ResponseEntity<?> getAllPRoducts() {
        try {
            return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while searching for products", HttpStatus.BAD_REQUEST);
        }        
    }

    @PostMapping("/new")
    public ResponseEntity<?> newProduct(@RequestBody ProductEntity product) {
        try {
            // Llama al método del servicio para guardar el producto con la categoría
            ProductEntity savedProduct = service.saveProduct(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while saving product with category", HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductEntity product) {
        try {
            if(product == null || product.getId_product() == null){
                return new ResponseEntity<>("Invalid product data", HttpStatus.BAD_REQUEST);
            }
            ProductEntity updateProduct = service.updateProduct(product.getId_product(), product);
            return new ResponseEntity<>(updateProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating prodcut", HttpStatus.BAD_REQUEST);
        }
        
        
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try {
            service.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting product", HttpStatus.BAD_REQUEST);
        }
    }

}
