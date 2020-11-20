package com.marcos.springapi.exception;

import org.springframework.http.HttpStatus;

public class AlreadyVotedException extends CustomException {
    public AlreadyVotedException(Long id) {
        super(HttpStatus.CONFLICT, "Associate with id: " + id.toString() + " already voted.");
    }
}
