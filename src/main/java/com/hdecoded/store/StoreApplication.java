package com.hdecoded.store;

import com.hdecoded.store.entities.User;
import com.hdecoded.store.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        UserRepository userRepository = context.getBean(UserRepository.class);

        var user = User.builder()
                .name("hdecoded")
                .email("hdecoded@hdecoded.com")
                .password("hdecoded")
                .build();

        userRepository.save(user);

        System.out.println(userRepository.findById(2L).orElseThrow());

        userRepository.findAll().forEach(u -> System.out.println(u.getEmail()));

        userRepository.deleteById(1L);
    }

}
