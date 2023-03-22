package com.example.product_service.repository;

import com.example.product_service.entity.ProductCategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoriesRepo extends JpaRepository<ProductCategoriesEntity,Long> {
}
