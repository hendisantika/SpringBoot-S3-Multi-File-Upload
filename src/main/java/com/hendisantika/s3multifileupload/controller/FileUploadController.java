package com.hendisantika.s3multifileupload.controller;

import com.hendisantika.s3multifileupload.service.S3FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Project : SpringBoot-S3-Multi-File-Upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/10/24
 * Time: 17.33
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final S3FileUploadService s3FileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileKey = "uploads/" + file.getOriginalFilename();

        try {
            String message = s3FileUploadService.uploadFile(file, fileKey);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
}
