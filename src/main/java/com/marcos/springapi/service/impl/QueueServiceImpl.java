package com.marcos.springapi.service.impl;

import com.marcos.springapi.data.domain.Associado;
import com.marcos.springapi.data.domain.Sessao;
import com.marcos.springapi.data.repository.AssociadoRepository;
import com.marcos.springapi.data.repository.SessaoRepository;
import com.marcos.springapi.service.QueueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private SessaoRepository sessaoRepository;


    /*  This is one of many ways we could handle the issue of posting closed sessions to a Queue
    *   I chose to do in this way with a Scheduled task for ease of use.
    *   this could be done with a task in the DB or other approaches
    *   but this is the simplest of them all.
    * */
    @Scheduled(fixedRate = 10000)
    @Override
    public void publishSession() {
        List<Sessao> list = sessaoRepository.findUnsyncedClosedSessions(LocalDateTime.now());
        list.forEach(s -> {
            jmsTemplate.convertAndSend(queue,s.getId().toString());
            Logger.getLogger("QueueService").log(Level.INFO, "Sessão ".concat(s.getId().toString()).concat(" colocada na fila."));
        });
    }

    @Override
    @JmsListener(destination = "local.inmemory.queue")
    public void consumeSession(String content) {
        Optional<Sessao> oSessao = sessaoRepository.findById(Long.decode(content));
        if (oSessao.isPresent()) {
            Sessao sessao = oSessao.get();
            sessao.setSincronizado(true);
            sessaoRepository.save(sessao);
            Logger.getLogger("QueueService").log(Level.INFO, "Sessão ".concat(sessao.getId().toString()).concat(" sincronizada."));
        }
    }
}
