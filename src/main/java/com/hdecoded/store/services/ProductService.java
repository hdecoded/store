package com.hdecoded.store.services;

import com.hdecoded.store.entities.Category;
import com.hdecoded.store.entities.Product;
import com.hdecoded.store.repositories.CategoryRepository;
import com.hdecoded.store.repositories.ProductRepository;
import com.hdecoded.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public void createProduct() {
        var category = Category.builder()
                .name("CATEGORY")
                .build();

        var product = Product.builder()
                .name("Product Name")
                .description("Product Description")
                .price(BigDecimal.valueOf(23.34))
                .category(category)
                .build();
        productRepository.save(product);
    }

    @Transactional
    public void createProductForExistingCategory() {
        var category = categoryRepository.findById((byte) 1).orElseThrow();

        var product3 = Product.builder()
                .name("Product Name 3")
                .description("Product Description 3")
                .price(BigDecimal.valueOf(23.34))
                .category(category)
                .build();
        productRepository.save(product3);
    }

    @Transactional
    public void addProductToWishList() {
        var user = userRepository.findById(3L).orElseThrow();
        var products = productRepository.findAll();
        products.forEach(user::addFavProduct);
        userRepository.save(user);
    }

    public void deleteProduct() {
        productRepository.deleteById(3L);
    }

}
