package com.andreheller.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andreheller.backend.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Integer>{
    
}
