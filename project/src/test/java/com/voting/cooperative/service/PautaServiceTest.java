package com.voting.cooperative.service;

import com.voting.cooperative.dto.PautaDTO;
import com.voting.cooperative.exception.ResourceNotFoundException;
import com.voting.cooperative.model.Pauta;
import com.voting.cooperative.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;

    private PautaDTO pautaDTO;
    private Pauta pauta;

    @BeforeEach
    void setUp() {
        pautaDTO = new PautaDTO();
        pautaDTO.setTitulo("Teste de Pauta");
        pautaDTO.setDescricao("Descrição de teste");

        pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTitulo("Teste de Pauta");
        pauta.setDescricao("Descrição de teste");
    }

    @Test
    void deveCriarPautaComSucesso() {
        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        PautaDTO resultado = pautaService.criarPauta(pautaDTO);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Teste de Pauta", resultado.getTitulo());
        assertEquals("Descrição de teste", resultado.getDescricao());
    }

    @Test
    void deveBuscarPautaComSucesso() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));

        PautaDTO resultado = pautaService.buscarPauta(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Teste de Pauta", resultado.getTitulo());
    }

    @Test
    void deveGerarExcecaoQuandoPautaNaoEncontrada() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            pautaService.buscarPauta(1L);
        });
    }

    @Test
    void deveListarPautasComSucesso() {
        Pauta pauta2 = new Pauta();
        pauta2.setId(2L);
        pauta2.setTitulo("Outra Pauta");

        when(pautaRepository.findAll()).thenReturn(Arrays.asList(pauta, pauta2));

        List<PautaDTO> resultado = pautaService.listarPautas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Teste de Pauta", resultado.get(0).getTitulo());
        assertEquals("Outra Pauta", resultado.get(1).getTitulo());
    }
}