package com.example.product_service.repository;

import com.example.product_service.entity.ProductCategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoriesRepo extends JpaRepository<ProductCategoriesEntity,Long> {
    List<ProductCategoriesEntity> getAllByCategoryId (Long categoryId);
    List<ProductCategoriesEntity> getAllByProductId (Long productId);
}
