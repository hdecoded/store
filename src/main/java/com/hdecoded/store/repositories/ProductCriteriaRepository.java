package com.hdecoded.store.repositories;

import com.hdecoded.store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {

    List<Product> findProductByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);

}
