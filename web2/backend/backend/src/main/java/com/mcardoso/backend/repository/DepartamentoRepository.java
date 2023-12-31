package com.mcardoso.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcardoso.backend.domain.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer>{
    
}
