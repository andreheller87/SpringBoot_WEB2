package com.andreheller.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.andreheller.backend.domain.Departamento;
import com.andreheller.backend.domain.Funcionario;
import com.andreheller.backend.repository.DepartamentoRepository;
import com.andreheller.backend.repository.FuncionarioRepository;



@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		
	}
    @Bean
	public CommandLineRunner demo(
		DepartamentoRepository deptoRep, FuncionarioRepository funcRep
	){


		return(args) ->{

			Departamento depto1 = new Departamento(null, "Finanças");
			Departamento depto2 = new Departamento(null, "Produção");
			deptoRep.save(depto1);
			deptoRep.save(depto2);

			Funcionario func1 = new Funcionario(null, "Andre",depto1);
			Funcionario func2 = new Funcionario(null, "Enzo", depto2);
			funcRep.save(func1);
			funcRep.save(func2);
			
			


		};
	}
}
