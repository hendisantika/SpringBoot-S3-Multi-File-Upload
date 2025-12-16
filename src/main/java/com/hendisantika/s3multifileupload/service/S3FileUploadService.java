package com.hendisantika.s3multifileupload.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Project : SpringBoot-S3-Multi-File-Upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/10/24
 * Time: 17.32
 * To change this template use File | Settings | File Templates.
 */
@Service
@ConditionalOnProperty(name = "uploadServiceType", havingValue = "s3", matchIfMissing = false)
public class S3FileUploadService {
    private final S3Client s3Client;

    @Value("${s3.bucketName}")
    private String bucketName;

    @Value("${s3.region}")
    private String region;

    public S3FileUploadService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file, String key) throws IOException {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            return "File uploaded successfully: " + key;
        } catch (S3Exception e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }
}
