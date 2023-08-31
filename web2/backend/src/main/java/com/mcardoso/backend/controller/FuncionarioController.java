package com.mcardoso.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcardoso.backend.domain.Departamento;
import com.mcardoso.backend.domain.Funcionario;
import com.mcardoso.backend.repository.DepartamentoRepository;
import com.mcardoso.backend.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcRepository;

        @Autowired
    DepartamentoRepository deptoRepository;

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> funcionarios = funcRepository.findAll();
        return ResponseEntity.ok().body(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Funcionario>> findById(@PathVariable Integer id) {
        Optional<Funcionario> funcionario = funcRepository.findById(id);
        return ResponseEntity.ok().body(funcionario);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Funcionario>> findByName(@PathVariable String nome) {
        return ResponseEntity.ok().body(funcRepository.findByName(nome));
    }

@PostMapping("/{id_depto}")
public  ResponseEntity<Funcionario> insFunc(@PathVariable Integer id_depto, @Valid @RequestBody Funcionario pFuncionario){
/* 
    Departamento  depto = deptoRepository.findById(id_depto);
  Funcionario novoFunc = new Funcionario();
 novoFunc.setId_funcionario(null);
 novoFunc.setNm_funcionario(pFuncionario.getNm_funcionario());
 novoFunc.setDepartamento_pai(depto);
 */
}

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delFunc(@PathVariable Integer id){
            funcRepository.deleteById(id);
            return ResponseEntity.noContent().build();
    }
}
