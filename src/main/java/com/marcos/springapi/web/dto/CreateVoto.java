package com.marcos.springapi.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateVoto {
    @NotNull
    private Long associadoId;
    @NotNull
    private Boolean aprovado;
}
