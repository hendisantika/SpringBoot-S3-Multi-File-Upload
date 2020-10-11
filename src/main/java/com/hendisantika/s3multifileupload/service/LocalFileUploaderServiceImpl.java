package com.hendisantika.s3multifileupload.service;

import com.hendisantika.s3multifileupload.exception.FileUploadFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * Project : s3-multi-file-upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/10/20
 * Time: 11.34
 */

@Service
@ConditionalOnProperty(
        name = "uploadServiceType",
        havingValue = "local")
public class LocalFileUploaderServiceImpl implements FileUploaderService {

    @Value("${fileUploader.path}")
    private String uploadPath;

    @Override
    public void upload(MultipartFile document) {
        File file = new File(getFileName(document));

        try {
            document.transferTo(file);
        } catch (Exception e) {
            throw new FileUploadFailedException(e);
        }
    }

    private String getFileName(MultipartFile document) {
        return uploadPath + document.getOriginalFilename();
    }
}
