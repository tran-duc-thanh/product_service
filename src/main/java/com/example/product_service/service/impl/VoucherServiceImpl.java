package com.example.product_service.service.impl;

import com.example.product_service.dto.object.VoucherDTO;
import com.example.product_service.dto.request.ProductVouchersRequestDTO;
import com.example.product_service.entity.ProductVouchersEntity;
import com.example.product_service.entity.VoucherEntity;
import com.example.product_service.repository.ProductVouchersRepo;
import com.example.product_service.repository.VoucherRepo;
import com.example.product_service.service.VoucherService;
import com.example.product_service.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepo voucherRepo;
    private final ProductVouchersRepo productVouchersRepo;
    private final ModelMapper modelMapper;

    public VoucherServiceImpl(VoucherRepo voucherRepo, ProductVouchersRepo productVouchersRepo, ModelMapper modelMapper) {
        this.voucherRepo = voucherRepo;
        this.productVouchersRepo = productVouchersRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VoucherEntity> getAll() {
        return voucherRepo.findAll();
    }

    @Override
    public VoucherEntity getOne(Long voucherId) {
        if (voucherRepo.existsById(voucherId))
            return voucherRepo.getById(voucherId);
        return null;
    }

    @Override
    public VoucherEntity save(VoucherDTO voucher) {
        VoucherEntity result = modelMapper.map(voucher, VoucherEntity.class);
        result.setStart(DateUtils.safeToInstant(voucher.getStart()));
        result.setEnd(DateUtils.safeToInstant(voucher.getEnd()));
        return voucherRepo.save(result);
    }

    @Override
    public void delete(Long voucherId) {
        if (voucherRepo.existsById(voucherId))
            voucherRepo.deleteById(voucherId);
    }

    @Override
    public List<ProductVouchersEntity> addVouchersToProduct(ProductVouchersRequestDTO request) {
        List<ProductVouchersEntity> results = new ArrayList<>();
        request.getVoucherIds().forEach(voucherId -> {
            ProductVouchersEntity data = new ProductVouchersEntity();
            data.setProductId(request.getProductId());
            data.setVoucherId(voucherId);
            results.add(data);
        });
        return productVouchersRepo.saveAll(results);
    }
}
