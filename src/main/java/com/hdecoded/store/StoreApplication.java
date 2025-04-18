package com.hdecoded.store;

import com.hdecoded.store.entities.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
//        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        User.builder()
                .id(1L)
                .name("hdecoded")
                .email("hd@hdecoded.com")
                .password("password")
                .build();
    }
}
