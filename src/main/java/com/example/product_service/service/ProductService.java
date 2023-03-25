package com.example.product_service.service;

import com.example.product_service.dto.object.ProductDTO;
import com.example.product_service.dto.request.SearchProductRequestDTO;
import com.example.product_service.entity.ImageEntity;
import com.example.product_service.entity.ProductEntity;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    Page<ProductDTO> search (SearchProductRequestDTO dataSearch, Pageable pageable);
    ProductDTO getOne (Long id);
    ProductEntity save (ProductEntity product);
    void delete (Long productId);
    List<ImageEntity> uploadImg (MultipartFile[] files, Long productId);
    ByteArrayResource downloadImg (Long imageId);
    void removeImg (Long imageId);
}
