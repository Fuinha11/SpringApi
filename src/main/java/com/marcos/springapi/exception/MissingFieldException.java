package com.marcos.springapi.exception;

import org.springframework.http.HttpStatus;

public class MissingFieldException extends CustomException {
    public MissingFieldException(String fieldName) {
        super(HttpStatus.BAD_REQUEST, "The field " + fieldName + "is missing.");
    }
}
