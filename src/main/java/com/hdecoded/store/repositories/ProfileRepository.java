package com.hdecoded.store.repositories;

import com.hdecoded.store.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
