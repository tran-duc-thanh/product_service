package com.example.product_service.controller;

import com.example.product_service.dto.object.VoucherDTO;
import com.example.product_service.dto.request.ProductVouchersRequestDTO;
import com.example.product_service.entity.ProductCategoriesEntity;
import com.example.product_service.entity.ProductVouchersEntity;
import com.example.product_service.entity.VoucherEntity;
import com.example.product_service.service.VoucherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voucher")
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public ResponseEntity<List<VoucherEntity>> getAll () {
        return ResponseEntity.ok(voucherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherEntity> getOne (@PathVariable("id") Long id) {
        return ResponseEntity.ok(voucherService.getOne(id));
    }

    @PostMapping("/save")
    public ResponseEntity<VoucherEntity> save (@RequestBody VoucherDTO voucher) {
        return ResponseEntity.ok(voucherService.save(voucher));
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable("id") Long id) {
        voucherService.delete(id);
    }

    @PostMapping("/add-vouchers")
    public ResponseEntity<List<ProductVouchersEntity>> addVouchersToProduct (@RequestBody ProductVouchersRequestDTO request) {
        return ResponseEntity.ok(voucherService.addVouchersToProduct(request));
    }
}
