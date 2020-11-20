package com.marcos.springapi.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sessoes")
public class Sessao implements Serializable {

    @Id
    @Column(name="ID", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne
    private Pauta pauta;

    @Column(name="data_inicio", updatable=false, nullable=false)
    private LocalDateTime dataInicio;

    @Column(name="data_fim", updatable=false, nullable=false)
    private LocalDateTime dataFim;

    @OneToMany(mappedBy = "sessao", fetch = FetchType.LAZY)
    private List<Voto> votos;

    public Sessao(Pauta pauta, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.pauta = pauta;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}
