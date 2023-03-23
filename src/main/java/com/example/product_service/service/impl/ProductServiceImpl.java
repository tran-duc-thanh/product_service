package com.example.product_service.service.impl;

import com.example.product_service.entity.ProductCategoriesEntity;
import com.example.product_service.entity.ProductEntity;
import com.example.product_service.repository.ProductCategoriesRepo;
import com.example.product_service.repository.ProductRepo;
import com.example.product_service.service.ProductService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ProductCategoriesRepo productCategoriesRepo;

    public ProductServiceImpl(ProductRepo productRepo, ProductCategoriesRepo productCategoriesRepo) {
        this.productRepo = productRepo;
        this.productCategoriesRepo = productCategoriesRepo;
    }

    @Override
    public List<ProductEntity> getAll() {
        return productRepo.findAll();
    }

    @Override
    public List<ProductEntity> getAllByCategory(Long categoryId) {
        return null;
    }

    @Override
    public ProductEntity getOne(Long id) {
        return null;
    }

    @Override
    public ProductEntity add(ProductEntity product) {
        return null;
    }

    @Override
    public ProductEntity update(ProductEntity product) {
        return null;
    }

    @Override
    public void delete(Long productId) {

    }

    @Override
    public void uploadImg(MultipartFile[] files) {

    }

    @Override
    public ByteArrayResource downloadImg(String path) {
        return null;
    }

    @Override
    public void removeImg(String path) {

    }
}
