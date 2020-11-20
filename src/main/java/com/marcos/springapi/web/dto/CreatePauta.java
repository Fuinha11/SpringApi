package com.marcos.springapi.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreatePauta {
    @NotBlank(message = "descricao cannot be null or empty")
    private String descricao;
}
