package com.hendisantika.s3multifileupload.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

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
}
