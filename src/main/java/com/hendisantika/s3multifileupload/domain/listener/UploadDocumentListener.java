package com.hendisantika.s3multifileupload.domain.listener;

import com.hendisantika.s3multifileupload.domain.event.UploadDocument;
import com.hendisantika.s3multifileupload.service.FileUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : s3-multi-file-upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/10/20
 * Time: 11.32
 */
@Component
public class UploadDocumentListener implements ApplicationListener<UploadDocument> {

    private final FileUploaderService fileUploaderService;

    @Autowired
    public UploadDocumentListener(FileUploaderService fileUploaderService) {
        this.fileUploaderService = fileUploaderService;
    }

    @Override
    public void onApplicationEvent(UploadDocument event) {
        fileUploaderService.upload(event.getDocument());
    }
}
