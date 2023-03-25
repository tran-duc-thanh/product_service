package com.example.product_service.dto.request;

import java.util.List;

public class ProductVouchersRequestDTO {
    private Long productId;
    private List<Long> voucherIds;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Long> getVoucherIds() {
        return voucherIds;
    }

    public void setVoucherIds(List<Long> voucherIds) {
        this.voucherIds = voucherIds;
    }
}
