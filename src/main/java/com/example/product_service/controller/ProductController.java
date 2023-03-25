package com.example.product_service.controller;

import com.example.product_service.dto.object.ProductDTO;
import com.example.product_service.dto.request.SearchProductRequestDTO;
import com.example.product_service.entity.ImageEntity;
import com.example.product_service.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/search")
    public ResponseEntity<Page<ProductDTO>> search (@RequestBody SearchProductRequestDTO searchData, Pageable pageable) {
        Page<ProductDTO> pageData = productService.search(searchData, pageable);
        return ResponseEntity.ok(pageData);
    }

    @PostMapping("/upload")
    public ResponseEntity<List<ImageEntity>> upload (@RequestParam("files") MultipartFile[] files,
                                                     @RequestParam("productId") Long productId) {
        return ResponseEntity.ok(productService.uploadImg(files, productId));
    }
}
