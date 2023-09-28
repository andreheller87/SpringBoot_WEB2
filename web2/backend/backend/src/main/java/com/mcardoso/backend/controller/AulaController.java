package com.mcardoso.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/aula")
public class AulaController {
    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<Aula>> findAll() {
        List<Aula> listaAulas = aulaRepository.findAll();
        return ResponseEntity.ok().body(listaAulas);
    }

    @PostMapping(value="/{id_aula}/aluno/{id_aluno}")
    public ResponseEntity<Aula> insAlunoAula(@Valid @PathVariable Integer id_aula, @Valid @PathVariable Integer id_aluno) {
        Aula aula = aulaRepository.findById(id_aula)
            .orElseThrow(
                () -> new ObjectNotFoundException("Aula "+id_aula+" não encontrada!")
            );
        Aluno aluno = alunoRepository.findById(id_aluno)
            .orElseThrow(
                () -> new ObjectNotFoundException("Aluno "+id_aluno+" não encontrado!")
            );
        List<Aluno> listaAlunos = aula.getAlunos();
        listaAlunos.add(aluno);
        aula.setAlunos(listaAlunos);
        aulaRepository.save(aula);
        return ResponseEntity.ok().body(aula);
    }
    @DeleteMapping("/{id_aula}/aluno/{id_aluno}")
    public ResponseEntity<Void> removeAlunoAula(@Valid @PathVariable Integer id_aula, @Valid @PathVariable Integer id_aluno) {
        Aula aula = aulaRepository.findById(id_aula)
            .orElseThrow(
                () -> new ObjectNotFoundException("Aula "+id_aula+" não encontrada!")
            );
        Aluno aluno = alunoRepository.findById(id_aluno)
            .orElseThrow(
                () -> new ObjectNotFoundException("Aluno "+id_aluno+" não encontrado!")
            );
        List<Aluno> listaAlunos = aula.getAlunos();
        listaAlunos.remove(aluno);
        aula.setAlunos(listaAlunos);
        aulaRepository.save(aula);
        return ResponseEntity.noContent().build();
    }
}
