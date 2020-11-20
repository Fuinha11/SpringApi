package com.marcos.springapi.service;

import com.marcos.springapi.data.domain.Sessao;
import com.marcos.springapi.data.domain.VotingResults;
import com.marcos.springapi.data.domain.Voto;
import com.marcos.springapi.exception.AlreadyVotedException;
import com.marcos.springapi.exception.CustomException;
import com.marcos.springapi.exception.ObjectNotFountException;
import com.marcos.springapi.exception.SessionClosedException;

public interface VotingService {
    Sessao createSessao(Long pautaId, Integer durationInMinutes) throws CustomException;
    Voto voteToSession(Long sessionId, Long associateId, Boolean approved) throws CustomException;
    VotingResults tallySession(Long sessionId) throws CustomException;
}
