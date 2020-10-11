package com.hendisantika.s3multifileupload.domain.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * Project : s3-multi-file-upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/10/20
 * Time: 11.26
 */
public class DocumentsUploaded extends ApplicationEvent {
    private final MultipartFile[] documents;

    public DocumentsUploaded(Object source, MultipartFile[] documents) {
        super(source);
        this.documents = documents;
    }

    public MultipartFile[] getDocuments() {
        return documents;
    }
}
