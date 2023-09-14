package com.mcardoso.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mcardoso.backend.domain.Departamento;
import com.mcardoso.backend.domain.Funcionario;
import com.mcardoso.backend.repository.DepartamentoRepository;
import com.mcardoso.backend.repository.FuncionarioRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
		DepartamentoRepository deptoRep,
		FuncionarioRepository funcRep
	) {
		return (args) -> {
			Departamento depto1 = new Departamento(null, "Finanças");
			Departamento depto2 = new Departamento(null, "Produção");
			deptoRep.save(depto1);
			deptoRep.save(depto2);
			funcRep.save(new Funcionario(null, "Marcos", depto2));
			funcRep.save(new Funcionario(null, "Rogério", depto1));
			funcRep.save(new Funcionario(null, "Cardoso", depto1));
			funcRep.save(new Funcionario(null, "João Pedro", depto2));
			funcRep.save(new Funcionario(null, "José Carlos", depto1));
		};
	}

}
