package com.voting.cooperative.controller;

import com.voting.cooperative.dto.ResultadoVotacaoDTO;
import com.voting.cooperative.dto.SessaoVotacaoDTO;
import com.voting.cooperative.model.SessaoVotacao;
import com.voting.cooperative.service.SessaoVotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/sessoes")
@Api(tags = "Sessões de Votação", description = "Operações relacionadas a sessões de votação")
public class SessaoVotacaoController {

    private final SessaoVotacaoService sessaoVotacaoService;

    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @PostMapping
    @ApiOperation("Abre uma sessão de votação para uma pauta")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Sessão de votação aberta com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos ou sessão já existente"),
        @ApiResponse(code = 404, message = "Pauta não encontrada")
    })
    public ResponseEntity<SessaoVotacao> abrirSessaoVotacao(@Valid @RequestBody SessaoVotacaoDTO sessaoVotacaoDTO) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.abrirSessaoVotacao(sessaoVotacaoDTO);
        return new ResponseEntity<>(sessaoVotacao, HttpStatus.CREATED);
    }

    @GetMapping("/{pautaId}/resultado")
    @ApiOperation("Obtém o resultado da votação de uma pauta")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Resultado obtido com sucesso"),
        @ApiResponse(code = 404, message = "Pauta ou sessão não encontrada")
    })
    public ResponseEntity<ResultadoVotacaoDTO> obterResultadoVotacao(@PathVariable Long pautaId) {
        ResultadoVotacaoDTO resultado = sessaoVotacaoService.obterResultadoVotacao(pautaId);
        return ResponseEntity.ok(resultado);
    }
}