package com.hendisantika.s3multifileupload.domain.listener;

import com.hendisantika.s3multifileupload.domain.event.DocumentsUploaded;
import com.hendisantika.s3multifileupload.domain.event.UploadDocument;
import com.hendisantika.s3multifileupload.service.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * Project : s3-multi-file-upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/10/20
 * Time: 11.28
 */
@Component
public class DocumentsUploadedListener implements ApplicationListener<DocumentsUploaded> {

    private final EventPublisher eventPublisher;

    @Autowired
    public DocumentsUploadedListener(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void onApplicationEvent(DocumentsUploaded event) {
        Arrays.stream(event.getDocuments()).forEach(document -> eventPublisher.publish(new UploadDocument(this,
                document)));
    }
}
