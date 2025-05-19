package com.voting.cooperative.repository;

import com.voting.cooperative.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    boolean existsByPautaIdAndCpfAssociado(Long pautaId, String cpfAssociado);
    
    @Query("SELECT COUNT(v) FROM Voto v WHERE v.pauta.id = :pautaId AND v.opcaoVoto = 'SIM'")
    long countVotosSim(@Param("pautaId") Long pautaId);
    
    @Query("SELECT COUNT(v) FROM Voto v WHERE v.pauta.id = :pautaId AND v.opcaoVoto = 'NAO'")
    long countVotosNao(@Param("pautaId") Long pautaId);
    
    @Query("SELECT COUNT(v) FROM Voto v WHERE v.pauta.id = :pautaId")
    long countTotalVotosByPautaId(@Param("pautaId") Long pautaId);
    
    Optional<Voto> findByPautaIdAndCpfAssociado(Long pautaId, String cpfAssociado);
}