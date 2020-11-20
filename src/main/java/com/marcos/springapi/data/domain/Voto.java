package com.marcos.springapi.data.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "votos")
public class Voto {

    @Id
    @Column(name="ID", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private long id;

    @ManyToOne()
    private Associado associado;

    @Column(name = "aprovado")
    private boolean aprovado;

    @ManyToOne
    private Sessao sessao;

    public Voto(Associado associado, boolean aprovado, Sessao sessao) {
        this.associado = associado;
        this.aprovado = aprovado;
        this.sessao = sessao;
    }
}