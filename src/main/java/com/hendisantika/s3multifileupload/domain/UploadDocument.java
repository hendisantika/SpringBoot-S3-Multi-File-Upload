package com.hendisantika.s3multifileupload.domain;

/**
 * Created by IntelliJ IDEA.
 * Project : s3-multi-file-upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/10/20
 * Time: 11.14
 */
public class UploadDocument extends ApplicationEvent {
    private final MultipartFile document;

    public UploadDocument(Object source, MultipartFile document) {
        super(source);
        this.document = document;
    }

    public MultipartFile getDocument() {
        return document;
    }
}
