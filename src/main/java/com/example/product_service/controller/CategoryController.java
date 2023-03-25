package com.example.product_service.controller;

import com.example.product_service.dto.request.ProductCategoriesRequestDTO;
import com.example.product_service.entity.CategoryEntity;
import com.example.product_service.entity.ProductCategoriesEntity;
import com.example.product_service.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getAll () {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryEntity> save (@RequestBody CategoryEntity category) {
        return ResponseEntity.ok(categoryService.save(category));
    }

    @PostMapping("/add-categories")
    public ResponseEntity<List<ProductCategoriesEntity>> addCategoriesToProduct (@RequestBody ProductCategoriesRequestDTO request) {
        return ResponseEntity.ok(categoryService.addCategoryToProduct(request));
    }
}
