package com.marcos.springapi.exception;

import org.springframework.http.HttpStatus;

public class UnableToVoteException extends CustomException {
    public UnableToVoteException(Long associateId) {
        super(HttpStatus.CONFLICT, "Associate with id: " + associateId + " is unable to vote.");
    }
}
