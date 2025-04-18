package com.hdecoded.store.user;

public interface UserRepository {
    void save(User user);
    boolean existsByEmail(String email);
}
