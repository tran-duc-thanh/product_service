package com.example.product_service.repository;

import com.example.product_service.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepo extends JpaRepository<VoucherEntity,Long> {
}
