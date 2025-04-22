package com.hdecoded.store.repositories;

import com.hdecoded.store.dtos.ProductSummary;
import com.hdecoded.store.dtos.ProductSummaryDTO;
import com.hdecoded.store.entities.Category;
import com.hdecoded.store.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    //String
    List<Product> findByName(String name);

    List<Product> findByNameLike(String name);

    List<Product> findByNameNotLike(String name);

    List<Product> findByNameContaining(String name);

    List<Product> findByNameStartingWith(String name);

    List<Product> findByNameEndingWith(String name);

    List<Product> findByNameContainingIgnoreCase(String name);

    // Numbers
    List<Product> findByPrice(BigDecimal price);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceLessThan(BigDecimal price);

    List<Product> findByPriceGreaterThanEqual(BigDecimal price);

    List<Product> findByPriceLessThanEqual(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    // Null
    List<Product> findByDescriptionNull();

    List<Product> findByDescriptionNotNull();

    //Multiple Conditions
    List<Product> findByDescriptionNullAndNameNull();

    // Sort (OrderBy)
    List<Product> findByNameOrderByPrice(String name);

    // Limit (Top/First)
    List<Product> findTop5ByNameOrderByPrice(String name);

    List<Product> findFirst5ByNameLikeOrderByPrice(String name);

    // Find Products whose prices are in a given range and sort by name

    // SQL
    @Query(value = "select * from products p where p.price between :min and :max order by p.name", nativeQuery = true)
    List<Product> findByPriceBetweenOrderByName(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    //JPQL
    @Query("select p from Product p where p.price between :min and :max order by p.name")
    List<Product> findByPriceBetweenOrderByNameJPQL(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    //Generated using jpa buddy by then name findByPriceBetweenOrderByName
    @Query("select p from Product p where p.price between :min and :max order by p.name")
    List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    // join and left join
    @Query("select p from Product p left join p.category where p.price between :min and :max order by p.name")
    List<Product> findProducts2(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    // Aggregate function count, sum and others
    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    // update data
    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(@Param("categoryId") Byte categoryId, @Param("newPrice") BigDecimal newPrice);

    @Query("select p.id, p.name from Product p where p.category = :category")
    List<ProductSummary> findByCategory(@Param("category") Category category);

    @Query("select new com.hdecoded.store.dtos.ProductSummaryDTO(p.id, p.name) from Product p where p.category = :category")
    List<ProductSummaryDTO> findByCategoryDTO(@Param("category") Category category);

}
