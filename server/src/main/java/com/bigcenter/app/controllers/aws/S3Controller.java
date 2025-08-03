package com.bigcenter.app.controllers.aws;

import com.bigcenter.app.services.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/file-upload")
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            String url_fileName = s3Service.uploadFile(file);
            return ResponseEntity.ok(url_fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed" + e.getMessage());
        }
    }
}
