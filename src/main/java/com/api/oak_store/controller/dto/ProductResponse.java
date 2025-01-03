package com.api.oak_store.controller.dto;

import com.api.oak_store.entity.Product;
import com.api.oak_store.entity.enums.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        String name,
        String description,
        BigDecimal price,
        Boolean available,
        Category category,
        String sku,
        Integer stockQuantity,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
    public ProductResponse(Product product) {
        this
            (
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailable(),
                product.getCategory(),
                product.getSku(),
                product.getStockQuantity(),
                product.getCreatedAt(),
                product.getUpdatedAt()
            );
    }
}
