package com.hdecoded.store.services;

import com.hdecoded.store.entities.Address;
import com.hdecoded.store.entities.User;
import com.hdecoded.store.repositories.AddressRepository;
import com.hdecoded.store.repositories.ProfileRepository;
import com.hdecoded.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;

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

}
