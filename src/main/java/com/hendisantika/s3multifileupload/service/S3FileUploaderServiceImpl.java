package com.hendisantika.s3multifileupload.service;

import com.hendisantika.s3multifileupload.exception.FileUploadFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : s3-multi-file-upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/10/20
 * Time: 11.35
 */
@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "uploadServiceType",
        havingValue = "s3")
public class S3FileUploaderServiceImpl implements FileUploaderService {

    private final S3Client s3Client;

    @Value("${s3.bucketName}")
    private String bucketName;

    @Override
    public void upload(MultipartFile document) {
        try {
            File file = convertToFile(document);
            String fileName = getFileName(document);

            uploadToS3(fileName, file);
            file.delete();
        } catch (IOException e) {
            throw new FileUploadFailedException(e);
        }
    }

    private void uploadToS3(String fileName, File file) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .acl(ObjectCannedACL.BUCKET_OWNER_FULL_CONTROL)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
        log.info("Upload File success: {}", fileName);
    }

    public String uploadFile(ByteArrayResource resource, String fileName) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        try (InputStream inputStream = resource.getInputStream()) {
            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(inputStream, resource.contentLength()));
            log.info("Upload File success: {}", fileName);
        } catch (Exception e) {
            log.error("Error while uploading to s3 file {} with error: {}", fileName, e.getMessage());
        }
        return fileName;
    }

    private String getFileName(MultipartFile document) {
        return LocalDateTime.now() + "-" + document.getOriginalFilename().replace(" ", "_");
    }

    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }

}
