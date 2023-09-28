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
import com.mcardoso.backend.services.DeptoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository deptoRepository;
    @Autowired
    private FuncionarioRepository funcRepository;
    @Autowired
    private DeptoServices deptoServices;

    @GetMapping
    public ResponseEntity<List<Departamento>> findAll() {
        List<Departamento> departamentos = deptoRepository.findAll();
        return ResponseEntity.ok().body(departamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Departamento>> findById(@PathVariable Integer id) {
        Optional<Departamento> departamento = deptoRepository.findById(id);
        return ResponseEntity.ok().body(departamento);
    }

    @GetMapping("/{id}/funcionarios")
    public ResponseEntity<List<Funcionario>> findByDepto(@PathVariable Integer id) {
        return ResponseEntity.ok().body(funcRepository.findByDepto(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delDepto(@PathVariable Integer id) {
        deptoServices.delDepartamento(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Departamento> insDepto(@Valid @RequestBody Departamento pDepto){
        pDepto.setId_depto(null);
        deptoRepository.save(pDepto);
        URI vURI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/departamento/{id}").buildAndExpand(pDepto.getId_depto()).toUri();
        return ResponseEntity.created(vURI).body(pDepto);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Departamento> updDepto(@Valid @PathVariable Integer id, @RequestBody Departamento pDepto) {
        Departamento deptoAtual =
            deptoRepository.findById(id)
                .orElseThrow(
                    () -> new ObjectNotFoundException("Departamento "+id+" não encontrado!")
                );
        deptoAtual.setNm_depto(pDepto.getNm_depto());
        deptoRepository.save(deptoAtual);
        return ResponseEntity.ok().body(deptoAtual);
    }

}
