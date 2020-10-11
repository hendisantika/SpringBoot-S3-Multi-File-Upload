package com.hendisantika.s3multifileupload.controller;

import com.hendisantika.s3multifileupload.service.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
}
