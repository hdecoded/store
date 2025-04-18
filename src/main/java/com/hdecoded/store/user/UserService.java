package com.hdecoded.store.user;

import com.hdecoded.store.notification.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    private UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            System.out.println("User already exists");
        else {
            userRepository.save(user);
            sendEmail(user.getEmail(), user.getName());
            System.out.println(userRepository);
        }
    }

    public void sendEmail(String email, String name) {
        notificationService.send(name + " user created", email);
    }
}
