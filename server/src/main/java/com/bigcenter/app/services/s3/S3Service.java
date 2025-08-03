package com.bigcenter.app.services.s3;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {
    String uploadFile(MultipartFile file) throws IOException;
}
