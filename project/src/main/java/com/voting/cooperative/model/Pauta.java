package com.voting.cooperative.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título da pauta é obrigatório")
    private String titulo;

    @Column(length = 1000)
    private String descricao;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @OneToOne(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SessaoVotacao sessaoVotacao;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Voto> votos = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }
}