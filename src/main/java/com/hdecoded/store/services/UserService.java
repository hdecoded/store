package com.hdecoded.store.services;

import com.hdecoded.store.entities.User;
import com.hdecoded.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

    //    @Transactional
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

    public void showRelatedEntities() {
        var user = userRepository.findById(1L).orElseThrow();
        System.out.println(user.getEmail());
    }

}
