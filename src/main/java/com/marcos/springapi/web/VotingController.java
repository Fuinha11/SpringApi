package com.marcos.springapi.web;

import com.marcos.springapi.data.domain.Sessao;
import com.marcos.springapi.data.domain.VotingResults;
import com.marcos.springapi.data.domain.Voto;
import com.marcos.springapi.service.VotingService;
import com.marcos.springapi.web.dto.BaseResponse;
import com.marcos.springapi.web.dto.CreateSessão;
import com.marcos.springapi.web.dto.CreateVoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class VotingController {

    @Autowired
    private VotingService votingService;

    @PostMapping(path = "/sessoes")
    public ResponseEntity createSessao(@Valid @RequestBody CreateSessão body) {
        Sessao sessao = votingService.createSessao(body.getPautaId(), body.getDuracao());
        return new BaseResponse(sessao).Created();
    }

    @PostMapping(path = "/sessao/{id}/vote")
    public ResponseEntity voteOnSession(@PathVariable Long id ,@Valid @RequestBody CreateVoto body) {
        Voto voto = votingService.voteToSession(id,body.getAssociadoId(), body.getAprovado());
        return new BaseResponse(voto).Created();
    }

    @GetMapping(path = "/sessao/{id}/resultados")
    public ResponseEntity tallySessao(@PathVariable Long id) {
        VotingResults results = votingService.tallySession(id);
        return new BaseResponse(results).Ok();
    }
}
