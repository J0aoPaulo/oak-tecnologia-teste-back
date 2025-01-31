package com.api.oak_store.service;

import com.api.oak_store.controller.dto.CreateProductRequest;
import com.api.oak_store.controller.dto.ProductResponse;
import com.api.oak_store.controller.dto.UpdateProductRequest;
import com.api.oak_store.entity.Product;
import com.api.oak_store.exception.ProductAlreadyExist;
import com.api.oak_store.exception.ProductNotFoundException;
import com.api.oak_store.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
            throw new ProductAlreadyExist("Product with name: " + request.name() + " already created");

        var product = mapper.toProduct(request);
        repository.save(product);
        return product.getProductId();
    }

    public Product updateProduct(UUID productId, UpdateProductRequest request) {
        var product = repository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + productId + " not found"));

        updateProductField(product, request);
        return repository.save(product);
    }

    private void updateProductField(Product product, UpdateProductRequest request) {
        Optional.ofNullable(request.name()).ifPresent(product::setName);
        Optional.ofNullable(request.description()).ifPresent(product::setDescription);
        Optional.ofNullable(request.price()).ifPresent(product::setPrice);
        Optional.ofNullable(request.available()).ifPresent(product::setAvailable);
        Optional.ofNullable(request.category()).ifPresent(product::setCategory);
        Optional.ofNullable(request.stockQuantity()).ifPresent(product::setStockQuantity);
        Optional.ofNullable(request.updateAt()).ifPresent(product::setUpdatedAt);
    }

    public List<ProductResponse> getAllProducts() {
        return repository
                .findAll()
                .stream()
                .map(this.mapper::fromProduct)
                .collect(Collectors.toList());
    }

    public List<Product> searchProductByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> filterProductsByValue(BigDecimal minValue, BigDecimal maxValue) {
        return repository.findByPriceBetween(minValue, maxValue);
    }

    public List<Product> getTopExpensive(int limit) {
        return repository.findTopExpensiveProducts(limit);
    }
}