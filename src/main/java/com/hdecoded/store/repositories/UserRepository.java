package com.hdecoded.store.repositories;

import com.hdecoded.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
