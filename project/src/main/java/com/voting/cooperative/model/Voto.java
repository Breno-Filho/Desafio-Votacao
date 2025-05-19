package com.voting.cooperative.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @Column(nullable = false)
    private String cpfAssociado;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O voto é obrigatório")
    private OpcaoVoto opcaoVoto;

    @Column(name = "data_voto")
    private LocalDateTime dataVoto;

    @PrePersist
    public void prePersist() {
        this.dataVoto = LocalDateTime.now();
    }

    public enum OpcaoVoto {
        SIM, NAO
    }
}