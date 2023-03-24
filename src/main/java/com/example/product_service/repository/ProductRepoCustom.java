package com.example.product_service.repository;

import com.example.product_service.dto.object.ProductDTO;
import com.example.product_service.dto.request.SearchProductRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepoCustom {
    Page<ProductDTO> search (SearchProductRequestDTO dataSearch, Pageable pageable);
}
