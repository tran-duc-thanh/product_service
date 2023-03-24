package com.example.product_service.dto.object;

public class ProductDTO {
    private Long id;
    private String title;
    private Integer quantity;
    private Double price;
    private Integer status;
    private String created;
    private String categoryName;
    private String categoryCode;
    private String pathImages;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String title, Integer quantity, Double price, Integer status, String created, String categoryName, String categoryCode, String pathImages) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.created = created;
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.pathImages = pathImages;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getPathImages() {
        return pathImages;
    }

    public void setPathImages(String pathImages) {
        this.pathImages = pathImages;
    }
}
