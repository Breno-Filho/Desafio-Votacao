package com.voting.cooperative.service;

import com.voting.cooperative.client.CpfValidatorClient;
import com.voting.cooperative.dto.VotoDTO;
import com.voting.cooperative.exception.BusinessException;
import com.voting.cooperative.model.Pauta;
import com.voting.cooperative.model.SessaoVotacao;
import com.voting.cooperative.model.Voto;
import com.voting.cooperative.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VotoService {

    private final VotoRepository votoRepository;
    private final PautaService pautaService;
    private final SessaoVotacaoService sessaoVotacaoService;
    private final CpfValidatorClient cpfValidatorClient;

    @Transactional
    public Voto registrarVoto(VotoDTO votoDTO) {
        Pauta pauta = pautaService.getPautaEntity(votoDTO.getPautaId());
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.buscarSessaoVotacao(votoDTO.getPautaId());
        
        if (!sessaoVotacao.isAberta()) {
            throw new BusinessException("Sessão de votação não está aberta");
        }
        
        if (votoRepository.existsByPautaIdAndCpfAssociado(votoDTO.getPautaId(), votoDTO.getCpfAssociado())) {
            throw new BusinessException("Associado já votou nesta pauta");
        }
        
        // Valida se o CPF pode votar
        if (!cpfValidatorClient.podeVotar(votoDTO.getCpfAssociado())) {
            throw new BusinessException("CPF não está habilitado para votar");
        }
        
        Voto voto = new Voto();
        voto.setPauta(pauta);
        voto.setCpfAssociado(votoDTO.getCpfAssociado());
        voto.setOpcaoVoto(votoDTO.getOpcaoVoto());
        
        return votoRepository.save(voto);
    }
}