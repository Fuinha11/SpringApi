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
        BaseResponse response;
        try {
            Sessao sessao = votingService.createSessao(body.getPautaId(), body.getDuracao());
            response = new BaseResponse(sessao);
        } catch (Exception e) {
            response = new BaseResponse(e);
        }
        return response.created();
    }

    @PostMapping(path = "/sessao/{id}/vote")
    public ResponseEntity voteOnSession(@PathVariable Long id ,@Valid @RequestBody CreateVoto body) {
        BaseResponse response;
        try {
            Voto voto = votingService.voteToSession(id, body.getAssociadoId(), body.getAprovado());
            response = new BaseResponse(voto);
        } catch (Exception e) {
            response = new BaseResponse(e);
        }
        return response.created();
    }

    @GetMapping(path = "/sessao/{id}/resultados")
    public ResponseEntity tallySessao(@PathVariable Long id) {
        BaseResponse response;
        try {
            VotingResults results = votingService.tallySession(id);
            response = new BaseResponse(results);
        } catch (Exception e) {
            response = new BaseResponse(e);
        }
        return response.created();
    }
}
