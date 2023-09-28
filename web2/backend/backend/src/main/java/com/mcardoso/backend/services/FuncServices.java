package com.mcardoso.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcardoso.backend.domain.Departamento;
import com.mcardoso.backend.domain.Funcionario;
import com.mcardoso.backend.exceptions.ObjectNotFoundException;
import com.mcardoso.backend.repository.DepartamentoRepository;
import com.mcardoso.backend.repository.FuncionarioRepository;

@Service
public class FuncServices {
    @Autowired
    private FuncionarioRepository funcRepository;

    @Autowired
    private DepartamentoRepository deptoRepository;

    public Funcionario insFunc(Funcionario pFuncionario, Integer pIdDepto) {
        Departamento deptoFunc =
            deptoRepository
                .findById(pIdDepto).orElseThrow(
                    () -> new ObjectNotFoundException("Departamento "+pIdDepto+" não encontrado!")
                );
        pFuncionario.setId_funcionario(null);
        pFuncionario.setDepartamento_pai(deptoFunc);
        return funcRepository.save(pFuncionario);
    }

    public void delFunc(Integer pIdFuncionario) {
        funcRepository
            .findById(pIdFuncionario)
            .orElseThrow(
                () -> new ObjectNotFoundException("Funcionário "+pIdFuncionario+" não encontrado!")
            );
        funcRepository.deleteById(pIdFuncionario);
    }

    public Funcionario updFunc(Integer pId, Funcionario pFuncionario) {
        Funcionario funcAtual =
            funcRepository.findById(pId)
                .orElseThrow(
                    () -> new ObjectNotFoundException("Funcionário "+pId+" não encontrado!")
                );
        funcAtual.setNm_funcionario(pFuncionario.getNm_funcionario());
        return funcRepository.save(funcAtual);
    }
}
