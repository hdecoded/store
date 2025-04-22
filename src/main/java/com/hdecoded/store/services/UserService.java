package com.hdecoded.store.services;

import com.hdecoded.store.entities.Address;
import com.hdecoded.store.entities.Product;
import com.hdecoded.store.entities.User;
import com.hdecoded.store.repositories.AddressRepository;
import com.hdecoded.store.repositories.ProductRepository;
import com.hdecoded.store.repositories.ProfileRepository;
import com.hdecoded.store.repositories.UserRepository;
import com.hdecoded.store.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("hdecoded")
                .email("hd@hdecoded.com")
                .password("hdecoded")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("persistent");
        } else {
            System.out.println("Transient / Detached");
        }

        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("persistent");
        } else {
            System.out.println("Transient / Detached");
        }
    }

    @Transactional
    public void showRelatedEntities() {
//        var user = userRepository.findById(1L).orElseThrow();
//        System.out.println(user.getEmail());
        var profile = profileRepository.findById(1L).orElseThrow();
        System.out.println(profile);
        System.out.println(profile.getUser().getEmail());
    }

    @Transactional
    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address);
        System.out.println(address.getUser().getEmail());
    }

    public void relatedEntities() {
        var user = User.builder()
                .name("hdecoded")
                .email("hd@hdecoded.com")
                .password("hdecoded")
                .build();

        var address = Address.builder()
                .zip(123)
                .street("street")
                .city("city")
                .state("state")
                .build();

        user.addAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void deleteRelatedEntities() {
        var user = userRepository.findById(3L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void fetchUser() {
        var user = userRepository.findByEmail("hd@hdecoded.com").orElseThrow();
        System.out.println(user);
    }

    @Transactional
    public void fetchAllUsers() {
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
                    System.out.println(u);
                    u.getAddresses().forEach(System.out::println);
                }
        );
    }

    @Transactional
    public void fetchLoyalUsers() {
        var loyalUsers = userRepository.findLoyalUsers(2);
        loyalUsers.forEach(u -> {
            System.out.println(u.getId() + ": " + u.getEmail());
        });
    }

    public void fetchProductsByExampleQuery() {
        var product = new Product();
        product.setName("p");

        var matcher = ExampleMatcher.matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example = Example.of(product, matcher);
        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    public void fetchProductsByCriteria() {
        var products = productRepository.findProductByCriteria("p", BigDecimal.valueOf(1), null);
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpec.hasname(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        productRepository.findAll(spec).forEach(System.out::println);
    }

}
