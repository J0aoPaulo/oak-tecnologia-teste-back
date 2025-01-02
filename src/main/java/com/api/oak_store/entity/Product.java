package com.api.oak_store.entity;

import com.api.oak_store.entity.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID productId;

    @Column(nullable = false)
    @NonNull
    String name;

    @Column(nullable = false)
    @NonNull
    String description;

    @Column(nullable = false)
    @NonNull
    BigDecimal price;

    @Column(nullable = false)
    @NonNull
    Boolean available = true;

    @Enumerated(EnumType.STRING)
    @NonNull
    Category category;

    @Column(nullable = false)
    @NonNull
    String sku;

    @Column(nullable = false)
    @NonNull
    Integer stockQuantity;

    @Column(nullable = false)
    @NonNull
    LocalDateTime createdAt;

    @Column(nullable = false)
    @NonNull
    LocalDateTime updatedAt;
}