package com.example.product_service.service;

import com.example.product_service.dto.BaseFileDTO;
import io.minio.GetObjectArgs;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import org.springframework.core.io.ByteArrayResource;

import java.io.InputStream;

public interface MinioService {
    ObjectWriteResponse uploadFile (PutObjectArgs object);
    InputStream downloadFile (GetObjectArgs object);
    ObjectWriteResponse uploadFileShareReq (BaseFileDTO file);
    ByteArrayResource downloadFileShareReq (String object);
    void removeFile (String object);
}
