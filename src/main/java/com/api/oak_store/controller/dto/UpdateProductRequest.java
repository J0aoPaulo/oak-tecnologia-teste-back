package com.api.oak_store.controller.dto;


import com.api.oak_store.entity.Product;
import com.api.oak_store.entity.enums.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateProductRequest(
        String name,
        String description,
        BigDecimal price,
        Boolean available,
        Category category,
        Integer stockQuantity,
        LocalDateTime updateAt
) {
    public UpdateProductRequest(Product product) {
        this(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailable(),
                product.getCategory(),
                product.getStockQuantity(),
                product.getUpdatedAt()
        );
    }
}