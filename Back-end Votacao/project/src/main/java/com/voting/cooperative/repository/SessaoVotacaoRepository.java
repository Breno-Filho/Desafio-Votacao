package com.voting.cooperative.repository;

import com.voting.cooperative.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

    Optional<SessaoVotacao> findByPautaId(Long pautaId);
    
    @Query("SELECT s FROM SessaoVotacao s WHERE s.status = 'ABERTA' AND s.dataEncerramento <= :now")
    List<SessaoVotacao> findExpiredOpenSessions(LocalDateTime now);
}