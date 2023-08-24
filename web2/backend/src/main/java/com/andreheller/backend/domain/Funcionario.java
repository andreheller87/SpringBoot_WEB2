package com.andreheller.backend.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Funcionario  implements Serializable{



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Funcionario;

    @NotEmpty(message = "Campo não pode ser vazio")
    @Length(min = 5, max =255,message="Campo so pode conter de 5 a 255 caracteris")
    private String nm_Funcionario;

    @ManyToOne
    @JoinColumn(name="id_depto")
    private Departamento departamento_pai;

    public Funcionario(Integer id_Funcionario,
            @NotEmpty(message = "Campo não pode ser vazio") @Length(min = 5, max = 255, message = "Campo so pode conter de 5 a 255 caracteris") String nm_Funcionario,
            Departamento departamento_pai) {
        this.id_Funcionario = id_Funcionario;
        this.nm_Funcionario = nm_Funcionario;
        this.departamento_pai = departamento_pai;
    }

    public Funcionario() {
    }

    public Integer getId_Funcionario() {
        return id_Funcionario;
    }

    public void setId_Funcionario(Integer id_Funcionario) {
        this.id_Funcionario = id_Funcionario;
    }

    public String getNm_Funcionario() {
        return nm_Funcionario;
    }

    public void setNm_Funcionario(String nm_Funcionario) {
        this.nm_Funcionario = nm_Funcionario;
    }

    public Departamento getDepartamento_pai() {
        return departamento_pai;
    }

    public void setDepartamento_pai(Departamento departamento_pai) {
        this.departamento_pai = departamento_pai;
    }


}
