package com.example.product_service.service;

import com.example.product_service.entity.ProductEntity;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getAll ();
    List<ProductEntity> getAllByCategory (Long categoryId);
    ProductEntity getOne (Long id);
    ProductEntity add (ProductEntity product);
    ProductEntity update (ProductEntity product);
    void delete (Long productId);
    void uploadImg (MultipartFile[] files);
    ByteArrayResource downloadImg (String path);
    void removeImg (String path);
}
