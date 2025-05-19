package com.voting.cooperative.dto;

import com.voting.cooperative.model.Voto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {

    @NotNull(message = "O ID da pauta é obrigatório")
    private Long pautaId;

    @NotBlank(message = "O CPF do associado é obrigatório")
    @Pattern(regexp = "^\\d{11}$", message = "CPF deve conter 11 dígitos numéricos")
    private String cpfAssociado;

    @NotNull(message = "A opção de voto é obrigatória")
    private Voto.OpcaoVoto opcaoVoto;
}