package com.marcos.springapi.web;

import com.marcos.springapi.data.domain.Sessao;
import com.marcos.springapi.data.domain.VotingResults;
import com.marcos.springapi.data.domain.Voto;
import com.marcos.springapi.exception.MissingFieldException;
import com.marcos.springapi.service.VotingService;
import com.marcos.springapi.web.dto.BaseResponse;
import com.marcos.springapi.web.dto.CreateSessao;
import com.marcos.springapi.web.dto.CreateVoto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @ApiOperation(value = "Cria uma nova Sessão de votação em uma Pauta.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Retorna a nova Sessão criada.")})
    @PostMapping(path = "/sessoes", produces="application/json", consumes="application/json")
    public ResponseEntity<BaseResponse<Sessao>> createSessao(@NotNull @RequestBody CreateSessao body) {
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

    @ApiOperation(value = "Cria um Voto em uma Sessão de votação.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Retorna o Voto criado.")})
    @PostMapping(path = "/sessao/{id}/vote", produces="application/json", consumes="application/json")
    public ResponseEntity<BaseResponse<Voto>> voteOnSession(@PathVariable Long id , @NotNull @RequestBody CreateVoto body) {
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

    @ApiOperation(value = "Contabiliza os votos da Sessão.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna os resultados da Sessão de votação.")})
    @GetMapping(path = "/sessao/{id}/resultados", produces="application/json")
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
