package com.voting.cooperative.controller;

import com.voting.cooperative.dto.PautaDTO;
import com.voting.cooperative.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pautas")
@Api(tags = "Pautas", description = "Operações relacionadas a pautas")
public class PautaController {

    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    @ApiOperation("Cria uma nova pauta")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Pauta criada com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos")
    })
    public ResponseEntity<PautaDTO> criarPauta(@Valid @RequestBody PautaDTO pautaDTO) {
        PautaDTO novaPauta = pautaService.criarPauta(pautaDTO);
        return new ResponseEntity<>(novaPauta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca uma pauta pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Pauta encontrada"),
        @ApiResponse(code = 404, message = "Pauta não encontrada")
    })
    public ResponseEntity<PautaDTO> buscarPauta(@PathVariable Long id) {
        PautaDTO pauta = pautaService.buscarPauta(id);
        return ResponseEntity.ok(pauta);
    }

    @GetMapping
    @ApiOperation("Lista todas as pautas")
    public ResponseEntity<List<PautaDTO>> listarPautas() {
        List<PautaDTO> pautas = pautaService.listarPautas();
        return ResponseEntity.ok(pautas);
    }
}