package com.api.oak_store.controller.dto;

public record ResponseLogin(String acessToken, Long expiresIn) {
}