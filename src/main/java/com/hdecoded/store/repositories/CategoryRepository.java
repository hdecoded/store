package com.hdecoded.store.repositories;

import com.hdecoded.store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Byte> {

}
