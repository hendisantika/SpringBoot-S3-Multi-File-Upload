package com.hendisantika.s3multifileupload.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.hendisantika.s3multifileupload.exception.FileUploadFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Project : s3-multi-file-upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/10/20
 * Time: 11.35
 */
@Service
@ConditionalOnProperty(
        name = "uploadServiceType",
        havingValue = "s3")
public class S3FileUploaderServiceImpl implements FileUploaderService {

    @Value("${s3.endpointUrl}")
    private String endpointUrl;
    @Value("${s3.accessKey}")
    private String accessKey;
    @Value("${s3.secretKey}")
    private String secretKey;
    @Value("${s3.bucketName}")
    private String bucketName;

    private AmazonS3 s3client;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client =
                AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion("us-east-1").build();
    }

    @Override
    public void upload(MultipartFile document) {
        try {
            File file = convertToFile(document);
            String fileName = getFileName(document);

            uploadToS3(fileName, file);
        } catch (IOException e) {
            throw new FileUploadFailedException(e);
        }
    }

}
