package com.bigcenter.app.services.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {
    @Value("${AWS_BUCKET_NAME}")
    private String bucketName;

    private final S3Client s3Client;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String originalName = file.getOriginalFilename().replaceAll("\\s+", "_");
        String fileName = "uploads/" + timeStamp + "_" + originalName;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .acl("public-read")
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

        return getPublicUrl(fileName);
    }

    public String getPublicUrl(String fileName) {
        return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
    }
}
