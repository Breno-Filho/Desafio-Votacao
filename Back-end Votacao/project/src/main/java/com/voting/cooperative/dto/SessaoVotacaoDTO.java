package com.voting.cooperative.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacaoDTO {

    @NotNull(message = "O ID da pauta é obrigatório")
    private Long pautaId;
    
    @Min(value = 1, message = "O tempo de duração deve ser de pelo menos 1 minuto")
    private Integer minutosValidade;
}