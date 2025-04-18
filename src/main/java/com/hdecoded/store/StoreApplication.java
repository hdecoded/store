package com.hdecoded.store;

import com.hdecoded.store.notification.NotificationService;
import com.hdecoded.store.user.User;
import com.hdecoded.store.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var userService = context.getBean(UserService.class);
        userService.registerUser(new User(1L, "test@test.com", "test", "test"));
        userService.registerUser(new User(1L, "test@test.com", "test", "test"));
        userService.registerUser(new User(1L, "test1@test.com", "test1", "test1"));
    }
}
