package com.api.oak_store.controller;

import com.api.oak_store.controller.dto.CreateProductRequest;
import com.api.oak_store.controller.dto.ProductResponse;
import com.api.oak_store.controller.dto.UpdateProductRequest;
import com.api.oak_store.entity.Product;
import com.api.oak_store.repository.ProductRepository;
import com.api.oak_store.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ResponseEntity<List<Product>> createProduct(@RequestBody @Valid CreateProductRequest request) {
        service.createProduct(request);

        List<Product> updatedList = repository.findAllByOrderByPriceAsc();
        return ResponseEntity.ok(updatedList);
    }

    @PutMapping("/{productId}")
    @Transactional
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable UUID productId, @Valid @RequestBody UpdateProductRequest request) {
        var updatedProduct = service.updateProduct(productId, request);
        return ResponseEntity.ok(new ProductResponse(updatedProduct));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProductsList() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        List<Product> productByName = service.searchProductByName(name);
        return ResponseEntity.ok(productByName);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProductsByValue(
            @RequestParam BigDecimal minValue,
            @RequestParam BigDecimal maxValue) {
        List<Product> products = service.filterProductsByValue(minValue, maxValue);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/top-expensive")
    public ResponseEntity<List<Product>> getTopExpensiveProducts(@RequestParam int limit) {
        List<Product> products = service.getTopExpensive(limit);
        return ResponseEntity.ok(products);
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