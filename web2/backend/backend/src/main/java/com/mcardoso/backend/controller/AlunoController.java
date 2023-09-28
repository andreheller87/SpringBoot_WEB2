package com.mcardoso.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcardoso.backend.domain.Aluno;
import com.mcardoso.backend.domain.Aula;
import com.mcardoso.backend.exceptions.ObjectNotFoundException;
import com.mcardoso.backend.repository.AlunoRepository;
import com.mcardoso.backend.repository.AulaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> listaAlunos = alunoRepository.findAll();
        return ResponseEntity.ok().body(listaAlunos);
    }

    @PostMapping(value="/{id_aluno}/aula/{id_aula}")
    public ResponseEntity<Aluno> insAulaAluno(@Valid @PathVariable Integer id_aluno, @Valid @PathVariable Integer id_aula) {
        Aluno aluno = alunoRepository.findById(id_aluno)
            .orElseThrow(
                () -> new ObjectNotFoundException("Aluno "+id_aluno+" não encontrado!")
            );
        Aula aula = aulaRepository.findById(id_aula)
            .orElseThrow(
                () -> new ObjectNotFoundException("Aula "+id_aula+" não encontrada!")
            );
        List<Aula> listaAulas = aluno.getAulas();
        listaAulas.add(aula);
        aluno.setAulas(listaAulas);
        alunoRepository.save(aluno);
        return ResponseEntity.ok().body(aluno);
    }
}
