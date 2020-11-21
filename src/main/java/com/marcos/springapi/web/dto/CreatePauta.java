package com.marcos.springapi.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreatePauta {
    @ApiModelProperty(value = "Descrição da Pauta")
    private String descricao;
}
