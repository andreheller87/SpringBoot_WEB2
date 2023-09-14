package com.mcardoso.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcardoso.backend.exceptions.ObjectNotFoundException;
import com.mcardoso.backend.repository.DepartamentoRepository;

@Service
public class DeptoServices {
    @Autowired
    private DepartamentoRepository deptoRepository;

    public void delDepartamento(Integer pIdDepartamento)  {
        deptoRepository
            .findById(pIdDepartamento)
            .orElseThrow(
                () -> new ObjectNotFoundException("Departamento "+pIdDepartamento+" não encontrado!")
            );
        try {
            deptoRepository.deleteById(pIdDepartamento);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new com.mcardoso.backend.exceptions.DataIntegrityViolationException("Departamento "+pIdDepartamento+" não pode ser excluído! Possui funcionários alocados!");
        }
    }
}
