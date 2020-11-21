package com.marcos.springapi.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateVoto {

    @ApiModelProperty(value = "Id do Associado votante")
    @NotNull
    private Long associadoId;

    @ApiModelProperty(value = "Valor do Voto, se aprova a pauta ou não")
    @NotNull
    private Boolean aprovado;
}
