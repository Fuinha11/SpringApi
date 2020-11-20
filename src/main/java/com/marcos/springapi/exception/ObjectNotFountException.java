package com.marcos.springapi.exception;

import org.springframework.http.HttpStatus;

public class ObjectNotFountException extends CustomException {
    public ObjectNotFountException(Long id, String objectName) {
        super(HttpStatus.NOT_FOUND,
                "The object " + objectName + " with id: " + id.toString() + " was not found.");
    }
}
