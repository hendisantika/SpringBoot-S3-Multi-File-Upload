package com.hendisantika.s3multifileupload.controller;

import com.hendisantika.s3multifileupload.domain.event.DocumentsUploaded;
import com.hendisantika.s3multifileupload.service.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * Project : s3-multi-file-upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/10/20
 * Time: 20.14
 */
@RestController
public class UploadResourceController {
    private final EventPublisher eventPublisher;

    @Autowired
    public UploadResourceController(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data", produces = {"application/json"})
    public void upload(@RequestParam(name = "files") MultipartFile[] files) {
        if (isEmpty(files)) {
            throw new IllegalArgumentException("Files missing");
        }

        eventPublisher.publish(new DocumentsUploaded(this, files));
    }

    private boolean isEmpty(@RequestParam(name = "files") MultipartFile[] files) {
        return files == null || files.length == 0;
    }
}
