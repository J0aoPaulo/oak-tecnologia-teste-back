package com.api.oak_store.controller;

import com.api.oak_store.controller.dto.CreateCostumerRequest;
import com.api.oak_store.controller.dto.CostumerResponse;
import com.api.oak_store.controller.dto.UpdateCostumerRequest;
import com.api.oak_store.service.CostumerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/costumers")
public class CostumerController {

    private final CostumerService service;

    public CostumerController(CostumerService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createCostumer(@Valid @RequestBody CreateCostumerRequest request) {
        UUID costumerId = service.createCostumer(request);
        return ResponseEntity.created(URI.create("/api/v1/costumers/" + costumerId)).build();
    }

    @PutMapping("/{costumerId}")
    @Transactional
    public ResponseEntity<CostumerResponse> updateCostumer(
            @PathVariable UUID costumerId,
            @Valid @RequestBody UpdateCostumerRequest request) {
        var updatedCostumer = service.updateCostumer(costumerId, request);
        return ResponseEntity.ok(updatedCostumer);
    }

    @GetMapping
    public ResponseEntity<List<CostumerResponse>> getAllCostumers() {
        return ResponseEntity.ok(service.getAllCostumers());
    }

    @GetMapping("/{costumerId}")
    public ResponseEntity<CostumerResponse> getCostumerById(@PathVariable UUID costumerId) {
        return ResponseEntity.of(service.getCostumerById(costumerId));
    }

    @DeleteMapping("/{costumerId}")
    public ResponseEntity<Void> deleteCostumer(@PathVariable UUID costumerId) {
        service.deleteCostumer(costumerId);
        return ResponseEntity.noContent().build();
    }
}
