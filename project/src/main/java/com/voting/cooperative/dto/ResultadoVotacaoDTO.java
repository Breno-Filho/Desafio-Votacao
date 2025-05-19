package com.voting.cooperative.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoVotacaoDTO {

    private Long pautaId;
    private String tituloPauta;
    private long totalVotos;
    private long totalSim;
    private long totalNao;
    private LocalDateTime dataEncerramento;
    private boolean sessaoEncerrada;
    
    public String getResultado() {
        if (!sessaoEncerrada) {
            return "Sessão de votação ainda está aberta";
        }
        
        if (totalSim > totalNao) {
            return "APROVADA";
        } else if (totalNao > totalSim) {
            return "REJEITADA";
        } else {
            return "EMPATE";
        }
    }
}