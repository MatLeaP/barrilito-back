package com.barrilito.barrilito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.barrilito.barrilito.model.CategoryEntity;
import com.barrilito.barrilito.serviceInterface.I_CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
@RequestMapping("/api/category")
@RequiredArgsConstructor
@CrossOrigin(origins ="http://127.0.0.1:5173/")
public class CategoryController {

    @Autowired
    I_CategoryService service;

    @GetMapping("/list")
    public ResponseEntity<?> getAllCategories() {
        try {
            return new ResponseEntity<>(service.getAllCategory(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while searching for categories", HttpStatus.BAD_REQUEST);
        }        
    }

    @PostMapping("/new")
    public ResponseEntity<?> newCategory(@RequestBody CategoryEntity category) {
        try {
            return new ResponseEntity<>(service.saveCategory(category), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while saving categorie", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryEntity category) {
        try {
            if(category == null || category.getId_category() == null){
                return new ResponseEntity<>("Invalid category data", HttpStatus.BAD_REQUEST);
            }
            CategoryEntity updateCategory = service.updateCategory(category.getId_category(), category);
            return new ResponseEntity<>(updateCategory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating category", HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            service.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting category", HttpStatus.BAD_REQUEST);
        }
    }
}
