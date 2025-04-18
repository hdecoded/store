package com.hdecoded.store.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements NotificationService {

    @Value("${email.host}")
    private String EmailHost;

    @Value("${email.port}")
    private String EmailPort;

    @Override
    public void send(String message, String recipientEmail) {
        System.out.println();
        System.out.println(message + " Email: " + "sent to " + recipientEmail + " using host: " + EmailHost + ":" + EmailPort);
    }
}
