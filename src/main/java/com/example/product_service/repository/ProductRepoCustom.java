package com.example.product_service.repository;

import com.example.product_service.dto.object.ProductDTO;
import com.example.product_service.dto.request.SearchProductRequestDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepoCustom {
    List<ProductDTO> search (SearchProductRequestDTO dataSearch, Pageable pageable);
}
