package com.example.product_service.repository;

import com.example.product_service.entity.ProductVouchersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVouchersRepo extends JpaRepository<ProductVouchersEntity,Long> {
}
