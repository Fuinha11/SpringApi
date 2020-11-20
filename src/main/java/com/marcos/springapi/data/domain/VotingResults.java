package com.marcos.springapi.data.domain;

import lombok.Data;

@Data
public class VotingResults {
    private Long pautaId;
    private String pautaDescription;
    private Long sessaoId;
    private Long quatidadeVotos;
    private Long votosSim;
    private Long votosNao;
    private Boolean pautaAprovada;
}
