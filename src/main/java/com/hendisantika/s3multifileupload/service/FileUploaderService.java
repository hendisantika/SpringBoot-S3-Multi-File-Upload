package com.hendisantika.s3multifileupload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * Project : s3-multi-file-upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/10/20
 * Time: 11.33
 */
public interface FileUploaderService {

    void upload(MultipartFile document);
}
