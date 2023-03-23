package com.example.product_service.service;

import com.example.product_service.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getAll ();
    CategoryEntity add (CategoryEntity category);
    CategoryEntity update(CategoryEntity category);
}
