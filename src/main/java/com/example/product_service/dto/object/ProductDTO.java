package com.example.product_service.dto.object;

import com.example.product_service.entity.CategoryEntity;
import com.example.product_service.entity.ImageEntity;
import com.example.product_service.entity.VoucherEntity;

import java.util.List;

public class ProductDTO {
    private Long id;
    private String title;
    private Integer quantity;
    private Double price;
    private Integer status;
    private String created;
    private List<CategoryEntity> categories;
    private List<ImageEntity> images;
    private List<VoucherEntity> vouchers;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String title, Integer quantity, Double price, Integer status, String created) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public List<VoucherEntity> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<VoucherEntity> vouchers) {
        this.vouchers = vouchers;
    }
}
