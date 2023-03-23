package com.example.product_service.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class FileUtils {
    public static InputStream base64ToInputStream (String base64) {
        byte[] data = Base64.getDecoder().decode(base64);
        return new ByteArrayInputStream(data);
    }

    public static String inputStreamToBase64 (InputStream inputStream) throws IOException {
        byte[] data = inputStream.readAllBytes();
        return Base64.getEncoder().encodeToString(data);
    }

    public static ByteArrayResource toByteArrayResource (InputStream inputStream) throws IOException {
        return new ByteArrayResource(IOUtils.toByteArray(inputStream));
    }
}
