package com.marcos.springapi.data.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Pautas")
public class Pauta {
    @ApiModelProperty(value = "Id da Pauta")
    @Id
    @Column(name="ID", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private long id;

    @ApiModelProperty(value = "Descrição da Pauta")
    @Column(name="descricao", updatable=false, nullable=false)
    private String descricao;

    @OneToMany(mappedBy = "pauta", fetch = FetchType.LAZY)
    private List<Sessao> sessoes;

    public Pauta(String descricao) {
        this.descricao = descricao;
    }
}
