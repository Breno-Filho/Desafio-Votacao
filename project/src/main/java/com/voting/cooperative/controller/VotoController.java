package com.voting.cooperative.controller;

import com.voting.cooperative.dto.VotoDTO;
import com.voting.cooperative.model.Voto;
import com.voting.cooperative.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/votos")
@Api(tags = "Votos", description = "Operações relacionadas a votos")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    @ApiOperation("Registra um voto em uma pauta")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Voto registrado com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos, sessão fechada ou associado já votou"),
        @ApiResponse(code = 404, message = "Pauta ou sessão não encontrada")
    })
    public ResponseEntity<Voto> votar(@Valid @RequestBody VotoDTO votoDTO) {
        Voto voto = votoService.registrarVoto(votoDTO);
        return new ResponseEntity<>(voto, HttpStatus.CREATED);
    }
}