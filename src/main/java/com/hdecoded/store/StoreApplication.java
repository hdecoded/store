package com.hdecoded.store;

import com.hdecoded.store.entities.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
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

        user.addTag("tag1");

        var profile = Profile.builder()
                .id(1L)
                .bio("bio text")
                .date_of_birth(LocalDate.parse("2003-08-28"))
                .build();
        user.addProfile(profile);

        System.out.println(user);

        var category = Category.builder()
                .id(1L)
                .name("category1")
                .build();
        var product = Product.builder()
                .id(1L)
                .name("hdecoded")
                .price(BigDecimal.valueOf(45.00))
                .build();

        category.addProduct(product);
        System.out.println(category);
    }

}
