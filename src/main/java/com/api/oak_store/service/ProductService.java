package com.api.oak_store.service;

import com.api.oak_store.controller.dto.CreateProductRequest;
import com.api.oak_store.controller.dto.UpdateProductRequest;
import com.api.oak_store.entity.Product;
import com.api.oak_store.exception.ProductAlreadyExist;
import com.api.oak_store.exception.ProductNotFound;
import com.api.oak_store.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UUID createProduct(CreateProductRequest request) {
        if(repository.existsByName(request.name()))
            throw new ProductAlreadyExist("Product with name: " + request.name() + "already created");

        var user = mapper.toProduct(request);
        return user.getProductId();
    }

    public Product updateProduct(UUID productId, UpdateProductRequest request) {
        var product = repository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFound("Product with id: " + productId + "not found"));

        product.setName(Optional.ofNullable(request.name()).orElse(product.getName()));
        product.setDescription(Optional.ofNullable(request.description()).orElse(product.getDescription()));
        product.setPrice(Optional.ofNullable(request.price()).orElse(product.getPrice()));
        product.setAvailable(Optional.ofNullable(request.available()).orElse(product.getAvailable()));
        product.setCategory(Optional.ofNullable(request.category()).orElse(product.getCategory()));
        product.setStockQuantity(Optional.ofNullable(request.stockQuantity()).orElse(product.getStockQuantity()));
        product.setUpdatedAt(Optional.ofNullable(request.updateAt()).orElse(product.getUpdatedAt()));

        return repository.save(product);
    }
}
