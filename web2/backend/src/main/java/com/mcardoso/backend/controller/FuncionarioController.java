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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mcardoso.backend.domain.Departamento;
import com.mcardoso.backend.domain.Funcionario;
import com.mcardoso.backend.repository.DepartamentoRepository;
import com.mcardoso.backend.repository.FuncionarioRepository;
import com.mcardoso.backend.services.FuncServices;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcRepository;
@Autowired
    FuncServices funcSevices;
      

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

@PostMapping( "/{id_depto}")
public  ResponseEntity<Funcionario> insFunc(@PathVariable Integer id_depto,  @RequestBody Funcionario pFuncionario){

    Funcionario novoFuncionario = funcSevices.insFunc( pFuncionario,id_depto);
    URI  vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoFuncionario.getId_funcionario()).toUri();
 return ResponseEntity.created(vUri).body(novoFuncionario);


}

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delFunc(@PathVariable Integer id){
            funcRepository.deleteById(id);
            return ResponseEntity.noContent().build();
    }
}
