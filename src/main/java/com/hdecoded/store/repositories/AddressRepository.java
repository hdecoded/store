package com.hdecoded.store.repositories;

import com.hdecoded.store.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
