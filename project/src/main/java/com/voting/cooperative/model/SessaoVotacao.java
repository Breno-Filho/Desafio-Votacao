package com.voting.cooperative.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;

    @Column(name = "data_encerramento")
    private LocalDateTime dataEncerramento;

    @Enumerated(EnumType.STRING)
    private StatusSessao status;

    public boolean isAberta() {
        return status == StatusSessao.ABERTA && 
               LocalDateTime.now().isBefore(dataEncerramento);
    }

    public boolean isEncerrada() {
        return status == StatusSessao.ENCERRADA || 
               LocalDateTime.now().isAfter(dataEncerramento);
    }

    public enum StatusSessao {
        ABERTA, ENCERRADA
    }
}