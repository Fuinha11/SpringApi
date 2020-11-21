package com.marcos.springapi.data.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "associados")
public class Associado {

    @ApiModelProperty(value = "Id do Associado")
    @Id
    @Column(name="ID", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private long id;

    @ApiModelProperty(value = "Nome do Associado")
    @Column(name="nome", updatable=false, nullable=false)
    private String nome;

    @ApiModelProperty(value = "CPF do Associado")
    @Column(name="cpf", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private String cpf;

    public Associado(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
}
