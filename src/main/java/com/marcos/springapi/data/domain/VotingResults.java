package com.marcos.springapi.data.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VotingResults {
    private Long pautaId;
    private String pautaDescription;
    private Long sessaoId;
    private Integer quatidadeVotos;
    private Integer votosSim;
    private Integer votosNao;
    private Boolean pautaAprovada;
}
