package com.hendisantika.s3multifileupload.service;

import org.springframework.context.ApplicationEvent;

/**
 * Created by IntelliJ IDEA.
 * Project : s3-multi-file-upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/10/20
 * Time: 11.29
 */
public interface EventPublisher {
    void publish(ApplicationEvent event);
}