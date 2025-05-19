package com.voting.cooperative.service;

import com.voting.cooperative.dto.PautaDTO;
import com.voting.cooperative.exception.ResourceNotFoundException;
import com.voting.cooperative.model.Pauta;
import com.voting.cooperative.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaDTO criarPauta(PautaDTO pautaDTO) {
        Pauta pauta = new Pauta();
        pauta.setTitulo(pautaDTO.getTitulo());
        pauta.setDescricao(pautaDTO.getDescricao());
        
        Pauta savedPauta = pautaRepository.save(pauta);
        return convertToDTO(savedPauta);
    }

    public PautaDTO buscarPauta(Long id) {
        Pauta pauta = pautaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pauta não encontrada com ID: " + id));
        return convertToDTO(pauta);
    }

    public List<PautaDTO> listarPautas() {
        return pautaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Pauta getPautaEntity(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pauta não encontrada com ID: " + id));
    }

    private PautaDTO convertToDTO(Pauta pauta) {
        PautaDTO dto = new PautaDTO();
        dto.setId(pauta.getId());
        dto.setTitulo(pauta.getTitulo());
        dto.setDescricao(pauta.getDescricao());
        return dto;
    }
}