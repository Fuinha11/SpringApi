package com.marcos.springapi.data.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
    private long cpf;

    public Associado(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
}
