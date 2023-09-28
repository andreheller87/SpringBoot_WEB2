package com.mcardoso.backend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mcardoso.backend.domain.Departamento;
import com.mcardoso.backend.domain.Funcionario;
import com.mcardoso.backend.exceptions.ObjectNotFoundException;
import com.mcardoso.backend.repository.DepartamentoRepository;
import com.mcardoso.backend.repository.FuncionarioRepository;
import com.mcardoso.backend.services.FuncServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcRepository;

    @Autowired
    private DepartamentoRepository deptoRepository;

    @Autowired
    private FuncServices funcServices;

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

    @PostMapping(value = "/{id_depto}")
    public ResponseEntity<Funcionario> insFunc(@PathVariable Integer id_depto,@Valid @RequestBody Funcionario pFuncionario) {
        Funcionario novoFuncionario = funcServices.insFunc(pFuncionario, id_depto);
        URI vURI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/funcionario/{id}").buildAndExpand(novoFuncionario.getId_funcionario()).toUri();
        //fromCurrentRequest().path("/{id}").buildAndExpand(novoFuncionario.getId_funcionario()).toUri();
        return ResponseEntity.created(vURI).body(novoFuncionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delFunc(@PathVariable Integer id) {
        //funcRepository.deleteById(id);
        funcServices.delFunc(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updFunc(@Valid @PathVariable Integer id, @RequestBody Funcionario pFuncionario) {
        Funcionario funcUpdated = funcServices.updFunc(id, pFuncionario);
        URI vUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/funcionario/{id}").buildAndExpand(funcUpdated.getId_funcionario()).toUri();
        return ResponseEntity.created(vUri).body(funcUpdated);
    }

    @PutMapping(value="{idFunc}/depto/{idDepto}")
    public ResponseEntity<Funcionario> updFuncDepto(@Valid @PathVariable Integer idFunc, @Valid @PathVariable Integer idDepto) {
        Funcionario funcUpdated =
            funcRepository.findById(idFunc)
                .orElseThrow(
                    () -> new ObjectNotFoundException("Funcionário "+idFunc+" não encontrado!")
                );
        Departamento novoDepto =
            deptoRepository.findById(idDepto)
                .orElseThrow(
                    () -> new ObjectNotFoundException("Departamento "+idDepto+" não encontrado!")
                );
        funcUpdated.setDepartamento_pai(novoDepto);
        funcRepository.save(funcUpdated);
        URI vUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/funcionario/{id}").buildAndExpand(funcUpdated.getId_funcionario()).toUri();
        return ResponseEntity.created(vUri).body(funcUpdated);
    }
}
