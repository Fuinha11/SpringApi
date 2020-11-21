package com.marcos.springapi.data.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VotingResults {

    @ApiModelProperty(value = "Id da Pauta")
    private Long pautaId;

    @ApiModelProperty(value = "Descrição da Pauta")
    private String pautaDescription;

    @ApiModelProperty(value = "Id da Sessão")
    private Long sessaoId;

    @ApiModelProperty(value = "Total de votos da Sessão")
    private Integer quatidadeVotos;

    @ApiModelProperty(value = "Total de votos a favor")
    private Integer votosSim;

    @ApiModelProperty(value = "Total de votos contra")
    private Integer votosNao;

    @ApiModelProperty(value = "Se a pauta foi aprovada ou não")
    private Boolean pautaAprovada;
}
