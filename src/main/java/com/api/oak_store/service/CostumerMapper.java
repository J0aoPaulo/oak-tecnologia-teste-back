package com.api.oak_store.service;

import com.api.oak_store.controller.dto.CreateCostumerRequest;
import com.api.oak_store.controller.dto.CostumerResponse;
import com.api.oak_store.controller.dto.UpdateCostumerRequest;
import com.api.oak_store.entity.Costumer;
import org.springframework.stereotype.Service;

@Service
public class CostumerMapper {

    public Costumer toCostumer(CreateCostumerRequest request) {
        return Costumer.builder()
                .costumerId(null)
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public void updateCostumerFields(Costumer costumer, UpdateCostumerRequest request) {
        if (request.name() != null) costumer.setName(request.name());
        if (request.email() != null) costumer.setEmail(request.email());
        if (request.password() != null) costumer.setPassword(request.password());
    }

    public CostumerResponse toCostumerResponse(Costumer costumer) {
        return new CostumerResponse(
                costumer.getName(),
                costumer.getEmail()
        );
    }
}