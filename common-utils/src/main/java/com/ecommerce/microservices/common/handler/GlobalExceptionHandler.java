package com.ecommerce.microservices.common.handler;

import com.finalproject.common.exception.ApplicationException;
import com.finalproject.common.exception.NotFoundException;
import com.finalproject.common.exception.ValidationException;
import com.finalproject.common.reponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ApiResponse buildResponse(ErrorCode errorCode, Map<String, String> additionalErrors) {
        Map<String, String> error = new HashMap<>();
        error.put("errorCode", errorCode.getCode());
        error.put("errorMessage", errorCode.getMessage());
        if (additionalErrors != null) {
            error.putAll(additionalErrors);
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.error(error);
        return apiResponse;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleNotFoundException(NotFoundException ex) {
        return buildResponse(ErrorCode.NOT_FOUND, null);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleValidationException(ValidationException ex) {
        return buildResponse(ErrorCode.BAD_REQUEST, ex.getErrors());
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleApplicationException(ApplicationException ex) {
        return buildResponse(ErrorCode.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex) {
        return ex.getMessage();
    }
}
