package com.andreheller.backend.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andreheller.backend.domain.Departamento;
import com.andreheller.backend.repository.DepartamentoRepository;

@RestController
@RequestMapping(value = "/departamento")
public class DepartamentoController {

    @Autowired
    DepartamentoRepository deptoRepository;
    
@GetMapping("/")
public ResponseEntity<List<Departamento>> findAll(){

List<Departamento> departamentos = deptoRepository.findAll();
return ResponseEntity.ok().body(departamentos);

}




}
