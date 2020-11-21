package com.marcos.springapi.data.repository;

import com.marcos.springapi.data.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    @Query("SELECT s FROM Sessao s WHERE sincronizado = FALSE AND dataFim < :date")
    public List<Sessao> findUnsyncedClosedSessions(@Param("date") LocalDateTime date);
}
