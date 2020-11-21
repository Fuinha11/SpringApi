package com.marcos.springapi.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateSessao {

    @ApiModelProperty(value = "ID da Pauta de Votação")
    @NotNull(message = "pautaId is a required field")
    private Long pautaId;

    @ApiModelProperty(value = "Duração da Sessão de votação")
    private Integer duracao;
}
