package com.andreheller.backend.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Departamento implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_depto;
    @NotEmpty(message = "Campo não pode ser vazio")
    @Length(min = 5, max =255,message="Campo so pode conter de 5 a 255 caracteris")
    private String nm_depto;

    public Departamento() {

    }

    public Departamento(Integer id_depto, String nm_depto) {
        this.id_depto = id_depto;
        this.nm_depto = nm_depto;
    }

    public String getNm_depto() {
        return nm_depto;
    }

    public void setNm_depto(String nm_depto) {
        this.nm_depto = nm_depto;
    }

    public Integer getId_depto() {
        return id_depto;
    }

    public void setId_depto(Integer id_depto) {
        this.id_depto = id_depto;
    }

}
