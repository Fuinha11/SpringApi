package com.marcos.springapi.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateSessão {
    @NotNull(message = "pautaId is a required field")
    private Long pautaId;
    private Integer duracao;
}
