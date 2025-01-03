package com.api.oak_store.controller.dto;


import com.api.oak_store.entity.enums.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotEmpty(message = "{required.name}")
        String name,
        @NotEmpty(message = "{required.description}")
        String description,
        @NotNull(message = "{required.price}")
        BigDecimal price,
        @NotNull(message = "{required.category}")
        Category category,
        @NotNull(message = "{required.stockQuantity}")
        Integer stockQuantity
) {
}
