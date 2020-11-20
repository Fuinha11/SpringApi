package com.marcos.springapi.exception;

import org.springframework.http.HttpStatus;

public class SessionClosedException extends CustomException {
    public SessionClosedException(Long sessionId) {
        super(HttpStatus.CONFLICT, "The session with id: " + sessionId + " is closed.");
    }
}
