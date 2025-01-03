package com.api.oak_store.controller;

import com.api.oak_store.controller.dto.CreateProductRequest;
import com.api.oak_store.controller.dto.UpdateProductRequest;
import com.api.oak_store.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController("/api/v1/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
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
    public ResponseEntity<>
}
