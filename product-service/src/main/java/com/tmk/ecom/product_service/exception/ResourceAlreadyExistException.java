package com.tmk.ecom.product_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistException extends RuntimeException {

    public ResourceAlreadyExistException(String resource, String field) {
        super(String.format("%s with %s already exists", resource, field));
    }

    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
