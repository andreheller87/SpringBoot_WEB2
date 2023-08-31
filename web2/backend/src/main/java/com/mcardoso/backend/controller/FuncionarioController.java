package com.mcardoso.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcardoso.backend.domain.Funcionario;
import com.mcardoso.backend.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcRepository;

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
}
