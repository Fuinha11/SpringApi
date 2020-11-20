package com.marcos.springapi.data.repository;

import com.marcos.springapi.data.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
}
