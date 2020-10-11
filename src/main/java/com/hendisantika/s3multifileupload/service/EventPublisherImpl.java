package com.hendisantika.s3multifileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : s3-multi-file-upload
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/10/20
 * Time: 11.33
 */
@Service
public class EventPublisherImpl implements EventPublisher {

    private final ApplicationContext context;

    @Autowired
    public EventPublisherImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void publish(ApplicationEvent event) {
        context.publishEvent(event);
    }
}
