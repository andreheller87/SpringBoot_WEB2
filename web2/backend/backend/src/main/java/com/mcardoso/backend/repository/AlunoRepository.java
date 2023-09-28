package com.mcardoso.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcardoso.backend.domain.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
    
}
