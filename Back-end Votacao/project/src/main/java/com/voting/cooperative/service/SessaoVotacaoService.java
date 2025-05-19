package com.voting.cooperative.service;

import com.voting.cooperative.dto.ResultadoVotacaoDTO;
import com.voting.cooperative.dto.SessaoVotacaoDTO;
import com.voting.cooperative.exception.BusinessException;
import com.voting.cooperative.exception.ResourceNotFoundException;
import com.voting.cooperative.model.Pauta;
import com.voting.cooperative.model.SessaoVotacao;
import com.voting.cooperative.repository.SessaoVotacaoRepository;
import com.voting.cooperative.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessaoVotacaoService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;
    private final VotoRepository votoRepository;

    @Transactional
    public SessaoVotacao abrirSessaoVotacao(SessaoVotacaoDTO sessaoVotacaoDTO) {
        Pauta pauta = pautaService.getPautaEntity(sessaoVotacaoDTO.getPautaId());
        
        Optional<SessaoVotacao> sessaoExistente = sessaoVotacaoRepository.findByPautaId(pauta.getId());
        if (sessaoExistente.isPresent()) {
            throw new BusinessException("Já existe uma sessão de votação para esta pauta");
        }
        
        int duracaoMinutos = (sessaoVotacaoDTO.getMinutosValidade() != null) 
                ? sessaoVotacaoDTO.getMinutosValidade() 
                : 1;
        
        LocalDateTime dataAbertura = LocalDateTime.now();
        LocalDateTime dataEncerramento = dataAbertura.plusMinutes(duracaoMinutos);
        
        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.setDataAbertura(dataAbertura);
        sessaoVotacao.setDataEncerramento(dataEncerramento);
        sessaoVotacao.setStatus(SessaoVotacao.StatusSessao.ABERTA);
        
        return sessaoVotacaoRepository.save(sessaoVotacao);
    }

    public SessaoVotacao buscarSessaoVotacao(Long pautaId) {
        return sessaoVotacaoRepository.findByPautaId(pautaId)
                .orElseThrow(() -> new ResourceNotFoundException("Sessão de votação não encontrada para a pauta com ID: " + pautaId));
    }

    @Transactional
    @Scheduled(fixedRate = 60000) // Executa a cada minuto
    public void encerrarSessoesExpiradas() {
        LocalDateTime agora = LocalDateTime.now();
        List<SessaoVotacao> sessoesExpiradas = sessaoVotacaoRepository.findExpiredOpenSessions(agora);
        
        for (SessaoVotacao sessao : sessoesExpiradas) {
            sessao.setStatus(SessaoVotacao.StatusSessao.ENCERRADA);
            sessaoVotacaoRepository.save(sessao);
            log.info("Sessão de votação encerrada automaticamente: {}", sessao.getId());
        }
    }

    public ResultadoVotacaoDTO obterResultadoVotacao(Long pautaId) {
        Pauta pauta = pautaService.getPautaEntity(pautaId);
        SessaoVotacao sessao = buscarSessaoVotacao(pautaId);
        
        long totalVotos = votoRepository.countTotalVotosByPautaId(pautaId);
        long votosSim = votoRepository.countVotosSim(pautaId);
        long votosNao = votoRepository.countVotosNao(pautaId);
        
        return ResultadoVotacaoDTO.builder()
                .pautaId(pautaId)
                .tituloPauta(pauta.getTitulo())
                .totalVotos(totalVotos)
                .totalSim(votosSim)
                .totalNao(votosNao)
                .dataEncerramento(sessao.getDataEncerramento())
                .sessaoEncerrada(sessao.isEncerrada())
                .build();
    }
}