package com.hdecoded.store;

import com.hdecoded.store.entities.Address;
import com.hdecoded.store.entities.Profile;
import com.hdecoded.store.entities.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
//        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        var user = User.builder()
                .id(1L)
                .name("hdecoded")
                .email("hd@hdecoded.com")
                .password("password")
                .build();

        var address = Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip(500081)
                .build();

        user.addAddress(address);
        System.out.println(user);

        user.addTag("tag1");
        System.out.println(user);

        var profile = Profile.builder()
                .id(1L)
                .bio("bio text")
                .date_of_birth(LocalDate.parse("2003-08-28"))
                .build();
        user.addProfile(profile);
        System.out.println(user);
    }

}
