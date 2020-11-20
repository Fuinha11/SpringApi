package com.marcos.springapi.web;

import com.marcos.springapi.data.domain.Sessao;
import com.marcos.springapi.data.domain.VotingResults;
import com.marcos.springapi.data.domain.Voto;
import com.marcos.springapi.exception.MissingFieldException;
import com.marcos.springapi.service.VotingService;
import com.marcos.springapi.web.dto.BaseResponse;
import com.marcos.springapi.web.dto.CreateSessão;
import com.marcos.springapi.web.dto.CreateVoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @PostMapping(path = "/sessoes")
    public ResponseEntity<BaseResponse<Sessao>> createSessao(@NotNull @RequestBody CreateSessão body) {
        BaseResponse response;
        try {
            if (Objects.isNull(body.getPautaId()))
                throw new MissingFieldException("pautaId");

            Sessao sessao = votingService.createSessao(body.getPautaId(), body.getDuracao());
            response = new BaseResponse(sessao);
        } catch (Exception e) {
            response = new BaseResponse(e);
        }
        return response.created();
    }

    @PostMapping(path = "/sessao/{id}/vote")
    public ResponseEntity<BaseResponse<Voto>> voteOnSession(@PathVariable Long id ,@NotNull @RequestBody CreateVoto body) {
        BaseResponse response;
        try {
            if (Objects.isNull(body.getAssociadoId()))
                throw new MissingFieldException("associadoId");


            if (Objects.isNull(body.getAprovado()))
                throw new MissingFieldException("aprovado");

            Voto voto = votingService.voteToSession(id, body.getAssociadoId(), body.getAprovado());
            response = new BaseResponse(voto);
        } catch (Exception e) {
            response = new BaseResponse(e);
        }
        return response.created();
    }

    @GetMapping(path = "/sessao/{id}/resultados")
    public ResponseEntity<BaseResponse<VotingResults>> tallySessao(@PathVariable Long id) {
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
