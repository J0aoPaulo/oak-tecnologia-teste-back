package com.api.oak_store.repository;

import com.api.oak_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    boolean existsByName(String name);

    List<Product> findAllByOrderByPriceAsc();

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceBetween(BigDecimal minValue, BigDecimal maxValue);

    @Query("SELECT p FROM Product p ORDER BY p.price DESC LIMIT :limit")
    List<Product> findTopExpensiveProducts(@Param("limit") int limit);
}