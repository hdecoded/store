package com.hdecoded.store.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
@Repository
public class InMemoryUserRepository implements UserRepository {
    private HashMap<String, User> users = new HashMap<>();

    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    public boolean existsByEmail(String email) {
        return users.containsKey(email);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(users);
    }
}
