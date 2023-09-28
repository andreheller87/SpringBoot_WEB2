package com.mcardoso.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcardoso.backend.domain.Aula;

public interface AulaRepository extends JpaRepository<Aula, Integer>{
    
}
