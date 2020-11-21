package com.marcos.springapi.service;

import com.marcos.springapi.data.domain.Associado;
import com.marcos.springapi.exception.UnableToVoteException;
import com.marcos.springapi.exception.ValidationServiceUnavailable;

public interface ExternalValidationService {
    Boolean isAbleToVote(Associado associado) throws UnableToVoteException, ValidationServiceUnavailable;
}
