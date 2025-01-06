package com.api.oak_store.service;

import com.api.oak_store.controller.dto.RequestLogin;
import com.api.oak_store.entity.Costumer;
import com.api.oak_store.repository.CostumerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final CostumerRepository costumerRepository;

    public UserService(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }

    public boolean isLoginCorrect(RequestLogin loginDto, PasswordEncoder passwordEncoder, Costumer costumer) {
        return passwordEncoder.matches(loginDto.password(), costumer.getPassword());
    }
}