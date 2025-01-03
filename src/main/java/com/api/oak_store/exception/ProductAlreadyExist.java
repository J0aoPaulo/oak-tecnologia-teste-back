package com.api.oak_store.exception;

public class ProductAlreadyExist extends RuntimeException {

    public ProductAlreadyExist(String message) {
        super(message);
    }
}
