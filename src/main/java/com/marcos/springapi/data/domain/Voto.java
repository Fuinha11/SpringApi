package com.marcos.springapi.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "votos")
public class Voto {

    @ApiModelProperty(value = "Id do Voto")
    @Id
    @Column(name="ID", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private long id;

    @ManyToOne()
    private Associado associado;

    @ApiModelProperty(value = "Valor do Voto, se aprova a pauta ou não")
    @Column(name = "aprovado")
    private boolean aprovado;

    @JsonIgnore
    @ManyToOne
    private Sessao sessao;

    public Voto(Associado associado, boolean aprovado, Sessao sessao) {
        this.associado = associado;
        this.aprovado = aprovado;
        this.sessao = sessao;
    }
}
