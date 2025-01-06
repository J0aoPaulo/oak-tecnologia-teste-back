package com.api.oak_store.service;

import com.api.oak_store.controller.dto.CreateCostumerRequest;
import com.api.oak_store.controller.dto.CostumerResponse;
import com.api.oak_store.controller.dto.UpdateCostumerRequest;
import com.api.oak_store.exception.CostumerNotFoundException;
import com.api.oak_store.repository.CostumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CostumerService {

    private final CostumerRepository repository;
    private final CostumerMapper mapper;

    public CostumerService(CostumerRepository repository, CostumerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UUID createCostumer(CreateCostumerRequest request) {
        var costumer = mapper.toCostumer(request);
        repository.save(costumer);
        return costumer.getCostumerId();
    }

    public CostumerResponse updateCostumer(UUID costumerId, UpdateCostumerRequest request) {
        var costumer = repository.findById(costumerId)
                .orElseThrow(() -> new CostumerNotFoundException("Costumer with id " + costumerId + " not found"));
        mapper.updateCostumerFields(costumer, request);
        repository.save(costumer);
        return mapper.toCostumerResponse(costumer);
    }

    public List<CostumerResponse> getAllCostumers() {
        return repository.findAll()
                .stream()
                .map(mapper::toCostumerResponse)
                .collect(Collectors.toList());
    }

    public CostumerResponse getCostumerById(UUID costumerId) {
        var costumer = repository.findById(costumerId)
                .orElseThrow(() -> new CostumerNotFoundException("Costumer with id " + costumerId + " not found"));
        return mapper.toCostumerResponse(costumer);
    }

    public void deleteCostumer(UUID costumerId) {
        if (!repository.existsById(costumerId)) {
            throw new CostumerNotFoundException("Costumer with id " + costumerId + " not found");
        }
        repository.deleteById(costumerId);
    }
}