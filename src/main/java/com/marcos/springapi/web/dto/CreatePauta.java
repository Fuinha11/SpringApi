package com.marcos.springapi.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreatePauta {
    private String descricao;
}
