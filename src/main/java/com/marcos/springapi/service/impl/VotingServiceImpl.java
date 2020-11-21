package com.marcos.springapi.service.impl;

import com.marcos.springapi.data.domain.*;
import com.marcos.springapi.data.repository.AssociadoRepository;
import com.marcos.springapi.data.repository.PautaRepository;
import com.marcos.springapi.data.repository.SessaoRepository;
import com.marcos.springapi.data.repository.VotoRepository;
import com.marcos.springapi.exception.*;
import com.marcos.springapi.service.ExternalValidationService;
import com.marcos.springapi.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class VotingServiceImpl implements VotingService {

    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private ExternalValidationService validationService;

    @Override
    public Sessao createSessao(Long pautaId, Integer durationInMinutes) throws ObjectNotFountException {
        Pauta pauta = getPauta(pautaId);

        if (Objects.isNull(durationInMinutes) || durationInMinutes < 1)
            durationInMinutes = 1;

        return sessaoRepository.save(
          new Sessao(
                  pauta,
                  LocalDateTime.now(),
                  LocalDateTime.now().plus(durationInMinutes, MINUTES)
          )
        );
    }

    @Override
    public Voto voteToSession(Long sessionId, Long associateId, Boolean approved) throws CustomException {

        Associado associado = getAssociado(associateId);

        if (!validationService.isAbleToVote(associado))
            throw new UnableToVoteException(associado.getId());

        Sessao sessao = getSessao(sessionId);

        if (sessao.getDataFim().isBefore(LocalDateTime.now()))
            throw new SessionClosedException(sessao.getId());

        for (Voto v : sessao.getVotos()) {
            if (v.getAssociado().equals(associado))
                throw new AlreadyVotedException(associado.getId());
        }

        return votoRepository.save(
                new Voto(
                        associado,
                        approved,
                        sessao
                )
        );
    }

    @Override
    public VotingResults tallySession(Long sessionId) throws ObjectNotFountException {

        Sessao sessao = getSessao(sessionId);

        int approvedVotes = 0;
        int declinedVotes = 0;

        for (Voto v : sessao.getVotos()) {
            if (v.isAprovado())
                approvedVotes++;
            else
                declinedVotes++;
        }

        return new VotingResults(
                sessao.getPauta().getId(),
                sessao.getPauta().getDescricao(),
                sessao.getId(),
                sessao.getVotos().size(),
                approvedVotes,
                declinedVotes,
                approvedVotes > declinedVotes
        );
    }

    private Pauta getPauta(Long pautaId) throws ObjectNotFountException {
        Optional<Pauta> oPauta = pautaRepository.findById(pautaId);
        if (oPauta.isEmpty())
            throw new ObjectNotFountException(pautaId, "Pauta");
        return oPauta.get();
    }

    private Associado getAssociado(Long associateId) throws ObjectNotFountException {
        Optional<Associado> oAssociado = associadoRepository.findById(associateId);
        if (oAssociado.isEmpty())
            throw new ObjectNotFountException(associateId, "Associado");
        return oAssociado.get();
    }

    private Sessao getSessao(Long sessionId) throws ObjectNotFountException {
        Optional<Sessao> oSessao = sessaoRepository.findById(sessionId);
        if (oSessao.isEmpty())
            throw new ObjectNotFountException(sessionId, "Sessao");
        return oSessao.get();
    }
}
