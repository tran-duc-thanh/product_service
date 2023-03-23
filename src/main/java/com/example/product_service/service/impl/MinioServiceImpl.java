package com.example.product_service.service.impl;

import com.example.product_service.dto.object.BaseFileDTO;
import com.example.product_service.service.MinioService;
import com.example.product_service.utils.FileUtils;
import io.minio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;

    public MinioServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Value("${minio.bucket.name}")
    private String bucketName;

    @Override
    public ObjectWriteResponse uploadFile(PutObjectArgs object) {
        try {
            return minioClient.putObject(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InputStream downloadFile(GetObjectArgs object) {
        StatObjectResponse statObject = this.stat(object.bucket(), object.object());
        if (statObject != null && statObject.size() > 0) {
            try {
                return minioClient.getObject(object);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public StatObjectResponse stat (String bucketName, String objectName) {
        try {
            return minioClient.statObject(StatObjectArgs.builder().bucket(bucketName)
                    .object(objectName)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObjectWriteResponse uploadFileShareReq(BaseFileDTO file) {
        PutObjectArgs object = PutObjectArgs.builder().bucket(bucketName)
                .object(String.format("%s/%s", file.getId(), file.getName()))
                .stream(FileUtils.base64ToInputStream(file.getBase64()), -1, 1_073_741_824)
                .build();
        return this.uploadFile(object);
    }

    @Override
    public ByteArrayResource downloadFileShareReq(String object) {
        GetObjectArgs getObject = GetObjectArgs.builder().bucket(bucketName)
                .object(object)
                .build();
        InputStream inputStream = this.downloadFile(getObject);
        try {
            return FileUtils.toByteArrayResource(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeFile(String object) {
        RemoveObjectArgs removeObject = RemoveObjectArgs.builder().bucket(bucketName)
                .object(object)
                .build();
        try {
            minioClient.removeObject(removeObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
