package com.api.oak_store.controller;

import com.api.oak_store.controller.dto.CreateProductRequest;
import com.api.oak_store.controller.dto.ProductResponse;
import com.api.oak_store.controller.dto.UpdateProductRequest;
import com.api.oak_store.repository.ProductRepository;
import com.api.oak_store.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;
    private final ProductRepository repository;

    public ProductController(ProductService service, ProductRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createProduct(@Valid CreateProductRequest request) {
        var productId = service.createProduct(request);

        return ResponseEntity.created(URI.create("/api/v1/product/" + productId)).build();
    }

    @PutMapping("/{productId}")
    @Transactional
    public ResponseEntity<UpdateProductRequest> updateProduct(@PathVariable UUID productId, UpdateProductRequest request) {
        var userUpdated = service.updateProduct(productId, request);

        return ResponseEntity.ok(new UpdateProductRequest(userUpdated));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") UUID productId) {
        if(repository.existsById(productId)) {
            repository.deleteById(productId);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}