package com.hdecoded.store;

import com.hdecoded.store.services.ProductService;
import com.hdecoded.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        ProductService productService = (ProductService) context.getBean("productService");
        UserService userService = (UserService) context.getBean("userService");
//        userService.findProdcutByCategoryCriteria();
        userService.fetchProductsBySpecifications(null, null, null, 2L);
    }

}
