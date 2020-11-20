package com.marcos.springapi.data.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sessoes")
public class Sessao {

    @Id
    @Column(name="ID", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private long id;

    @ManyToOne
    private Pauta pauta;

    @Column(name="data_inicio", updatable=false, nullable=false)
    private LocalDateTime dataInicio;

    @Column(name="data_fim", updatable=false, nullable=false)
    private LocalDateTime data_fim;

    @OneToMany(mappedBy = "sessao")
    private List<Voto> votos;

    public Sessao(Pauta pauta, LocalDateTime dataInicio, LocalDateTime data_fim) {
        this.pauta = pauta;
        this.dataInicio = dataInicio;
        this.data_fim = data_fim;
    }
}
