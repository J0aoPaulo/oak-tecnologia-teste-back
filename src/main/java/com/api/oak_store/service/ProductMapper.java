package com.api.oak_store.service;

import com.api.oak_store.controller.dto.CreateProductRequest;
import com.api.oak_store.controller.dto.UpdateProductRequest;
import com.api.oak_store.entity.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProductMapper {


    public Product toProduct(CreateProductRequest request) {
        return Product.builder()
                .productId(null)
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .available(true)
                .category(request.category())
                .sku(generateSku(request.name()))
                .stockQuantity(request.stockQuantity())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    private String generateSku(String productName) {
        return productName.substring(0, 3).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 8);
    }

    public Product updateProductRequest(Product product, UpdateProductRequest request) {
        return Product.builder()
                .name()
    }

    private <T> T va
}
