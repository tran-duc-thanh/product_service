package com.example.product_service.service;

import com.example.product_service.dto.object.VoucherDTO;
import com.example.product_service.dto.request.ProductVouchersRequestDTO;
import com.example.product_service.entity.ProductVouchersEntity;
import com.example.product_service.entity.VoucherEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherService {
    List<VoucherEntity> getAll ();
    VoucherEntity getOne (Long voucherId);
    VoucherEntity save (VoucherDTO voucher);
    void delete (Long voucherId);
    List<ProductVouchersEntity> addVouchersToProduct (ProductVouchersRequestDTO request);
}
