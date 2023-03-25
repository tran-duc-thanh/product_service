package com.example.product_service.service.impl;

import com.example.product_service.dto.request.ProductCategoriesRequestDTO;
import com.example.product_service.entity.CategoryEntity;
import com.example.product_service.entity.ProductCategoriesEntity;
import com.example.product_service.repository.CategoryRepo;
import com.example.product_service.repository.ProductCategoriesRepo;
import com.example.product_service.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ProductCategoriesRepo productCategoriesRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ProductCategoriesRepo productCategoriesRepo) {
        this.categoryRepo = categoryRepo;
        this.productCategoriesRepo = productCategoriesRepo;
    }


    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepo.findAll();
    }

    @Override
    public CategoryEntity save(CategoryEntity category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<ProductCategoriesEntity> addCategoryToProduct(ProductCategoriesRequestDTO requestData) {
        List<ProductCategoriesEntity> results = new ArrayList<>();
        requestData.getCategoryIds().forEach(categoryId -> {
            ProductCategoriesEntity data = new ProductCategoriesEntity();
            data.setCategoryId(categoryId);
            data.setProductId(requestData.getProductId());
            results.add(data);
        });
        return productCategoriesRepo.saveAll(results);
    }

}
