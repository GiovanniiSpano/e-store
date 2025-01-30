package com.ecommerce.e_store.exception;

public class APIException extends RuntimeException {

    public APIException() {

    }

    public APIException(String message) {
        super(message);
    }
}
