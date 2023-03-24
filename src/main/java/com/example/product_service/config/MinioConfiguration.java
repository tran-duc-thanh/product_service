package com.example.product_service.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {

    @Value("${minio.access.key}")
    private String accessKey;

    @Value("${minio.access.secret}")
    private String secretKey;

    @Value("${minio.url}")
    private String endpoint;

    @Bean
    public MinioClient initMinioClient() {
        return MinioClient.builder().credentials(accessKey, secretKey).endpoint(endpoint).build();
    }
}
