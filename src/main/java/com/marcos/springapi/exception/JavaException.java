package com.marcos.springapi.exception;

import org.springframework.http.HttpStatus;

public class JavaException extends CustomException {
    public JavaException(Exception e) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
