package com.ecommerce.microservices.common.handler;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FOUND("404", "NOT_FOUND"),
    UNAUTHENTICATED("403", "UNAUTHENTICATED"),
    BAD_REQUEST("400", "BAD_REQUEST"),
    INTERNAL_SERVER_ERROR("500", "INTERNAL_SERVER_ERROR");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
