package com.ecommerce.microservices.common.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }

}
