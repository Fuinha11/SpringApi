package com.marcos.springapi.data.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "associados")
public class Associado {

    @Id
    @Column(name="ID", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private long id;

    @Column(name="nome", updatable=false, nullable=false)
    private String nome;

    @Column(name="cpf", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private long cpf;

    public Associado(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
}
