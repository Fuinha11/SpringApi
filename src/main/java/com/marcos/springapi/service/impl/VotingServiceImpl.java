package com.marcos.springapi.service.impl;

import com.marcos.springapi.data.domain.*;
import com.marcos.springapi.data.repository.AssociadoRepository;
import com.marcos.springapi.data.repository.PautaRepository;
import com.marcos.springapi.data.repository.SessaoRepository;
import com.marcos.springapi.data.repository.VotoRepository;
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

    @Override
    public Sessao createSessao(Long pautaId, Integer durationInMinutes) {
        Optional<Pauta> oPauta = pautaRepository.findById(pautaId);
        if (oPauta.isEmpty())
            return null;

        if (Objects.isNull(durationInMinutes) || durationInMinutes < 1)
            durationInMinutes = 1;

        return sessaoRepository.save(
          new Sessao(
                  oPauta.get(),
                  LocalDateTime.now(),
                  LocalDateTime.now().plus(durationInMinutes, MINUTES)
          )
        );
    }

    @Override
    public Voto voteToSession(Long sessionId, Long associateId, Boolean approved) {
        Optional<Associado> oAssociado = associadoRepository.findById(associateId);
        if (oAssociado.isEmpty())
            return null;
        Associado associado = oAssociado.get();

        Optional<Sessao> oSessao = sessaoRepository.findById(sessionId);
        if (oSessao.isEmpty())
            return null;
        Sessao sessao = oSessao.get();

        if (sessao.getDataFim().isBefore(LocalDateTime.now()))
            return null;

        for (Voto v : sessao.getVotos()) {
            if (v.getAssociado().equals(associado))
                return null;
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
    public VotingResults tallySession(Long sessionId) {

        Optional<Sessao> oSessao = sessaoRepository.findById(sessionId);
        if (oSessao.isEmpty())
            return null;
        Sessao sessao = oSessao.get();

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
}
