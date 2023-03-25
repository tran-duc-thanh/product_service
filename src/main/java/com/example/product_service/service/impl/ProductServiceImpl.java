package com.example.product_service.service.impl;

import com.example.product_service.dto.object.BaseFileDTO;
import com.example.product_service.dto.object.ProductDTO;
import com.example.product_service.dto.request.SearchProductRequestDTO;
import com.example.product_service.entity.CategoryEntity;
import com.example.product_service.entity.ImageEntity;
import com.example.product_service.entity.ProductEntity;
import com.example.product_service.entity.VoucherEntity;
import com.example.product_service.repository.*;
import com.example.product_service.service.MinioService;
import com.example.product_service.service.ProductService;
import com.example.product_service.utils.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ImageRepo imageRepo;
    private final VoucherRepo voucherRepo;
    private final ProductCategoriesRepo productCategoriesRepo;
    private final ProductVouchersRepo productVouchersRepo;
    private final MinioService minioService;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo, ImageRepo imageRepo, VoucherRepo voucherRepo, ProductCategoriesRepo productCategoriesRepo, ProductVouchersRepo productVouchersRepo, MinioService minioService) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.imageRepo = imageRepo;
        this.voucherRepo = voucherRepo;
        this.productCategoriesRepo = productCategoriesRepo;
        this.productVouchersRepo = productVouchersRepo;
        this.minioService = minioService;
    }

    @Override
    public Page<ProductDTO> search(SearchProductRequestDTO dataSearch, Pageable pageable) {
        Page<ProductDTO> result = productRepo.search(dataSearch, pageable);
        result.getContent().forEach(product -> {
            List<CategoryEntity> categories = new ArrayList<>();
            productCategoriesRepo.getAllByProductId(product.getId()).forEach(item -> {
                categories.add(categoryRepo.getById(item.getCategoryId()));
            });
            product.setCategories(categories);
            product.setImages(imageRepo.getAllByProductId(product.getId()));
            List<VoucherEntity> vouchers = new ArrayList<>();
            productVouchersRepo.getAllByProductId(product.getId()).forEach(item -> {
                vouchers.add(voucherRepo.getById(item.getVoucherId()));
            });
        });
        return result;
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
        if (productRepo.existsById(productId)) productRepo.deleteById(productId);
    }

    @Override
    public List<ImageEntity> uploadImg(MultipartFile[] files, Long productId) {
        List<String> paths = new ArrayList<>();
        for (MultipartFile file : files) {
            BaseFileDTO baseFile = new BaseFileDTO();
            try {
                baseFile.setBase64(FileUtils.inputStreamToBase64(file.getInputStream()));
                baseFile.setName(FilenameUtils.getName(file.getOriginalFilename()));
                baseFile.setId(System.currentTimeMillis());
                minioService.uploadFileShareReq(baseFile);
                paths.add(String.format("/%s/%s", baseFile.getId(), baseFile.getName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        List<ImageEntity> images = new ArrayList<>();
        paths.forEach(path -> {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setProductId(productId);
            imageEntity.setUrl(path);
            images.add(imageEntity);
        });
        return imageRepo.saveAll(images);
    }

    @Override
    public ByteArrayResource downloadImg(String path) {
        return null;
    }

    @Override
    public void removeImg(String path) {

    }
}
