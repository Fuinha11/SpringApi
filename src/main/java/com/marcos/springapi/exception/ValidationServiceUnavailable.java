package com.marcos.springapi.exception;

import org.springframework.http.HttpStatus;

public class ValidationServiceUnavailable extends CustomException {
    public ValidationServiceUnavailable() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Validation Service is Unavailable");
    }
}
