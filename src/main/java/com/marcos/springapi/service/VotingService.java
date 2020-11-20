package com.marcos.springapi.service;

import com.marcos.springapi.data.domain.Sessao;
import com.marcos.springapi.data.domain.VotingResults;
import com.marcos.springapi.data.domain.Voto;

public interface VotingService {
    Sessao createSessao(Long pautaId, Integer durationInMinutes);
    Voto voteToSession(Long sessionId, Long associateId, Boolean approved);
    VotingResults tallySession(Long sessionId);
}
