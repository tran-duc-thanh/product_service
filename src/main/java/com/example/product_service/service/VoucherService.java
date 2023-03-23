package com.example.product_service.service;

import com.example.product_service.entity.VoucherEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherService {
    List<VoucherEntity> getAll ();
    VoucherEntity getOne (Long voucherId);
    VoucherEntity add (VoucherEntity voucher);
    VoucherEntity update (VoucherEntity voucher);
    void delete (Long voucherId);
}
