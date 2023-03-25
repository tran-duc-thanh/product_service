package com.example.product_service.service;

import com.example.product_service.dto.request.ProductCategoriesRequestDTO;
import com.example.product_service.entity.CategoryEntity;
import com.example.product_service.entity.ProductCategoriesEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getAll ();
    CategoryEntity save (CategoryEntity category);
    List<ProductCategoriesEntity> addCategoryToProduct (ProductCategoriesRequestDTO requestData);
}
