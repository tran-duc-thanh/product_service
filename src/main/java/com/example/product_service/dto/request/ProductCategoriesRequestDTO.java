package com.example.product_service.dto.request;

import java.util.List;

public class ProductCategoriesRequestDTO {
    Long productId;
    List<Long> categoryIds;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
