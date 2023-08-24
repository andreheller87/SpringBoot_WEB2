package com.andreheller.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class GeneralController {
    @GetMapping("/")
    public String index(){
        return"Hello, mundo!";
    }

    @GetMapping("/outroEP")
    public String outroEP() {
        return "Outro";
    }

    @PostMapping("/")
    public String indexPost() {
        return "via poster";
    }

    @GetMapping("/outroEP/{parametro}")
    public String index2Parametro(@PathVariable Integer parametro) {
        return "Você passou " + parametro + " como parametro";
    }

    @GetMapping(value="/geraJSON",produces = "application/json" )
    public String retornaJson() {
        return "{\"nome\": \"Marcos\"}";
    }
}
