package com.udea.festivos.apirest_festivos.core.entidades;

/**
 * @author Pedro Arango Sánchez
 * @author David Andrés Montoya Castaño
 * Universidad de Antioquia
 * Técnicas de Programación y Laboratorio [2554307] 
 * Grupo: 01 | Semestre: 2024-1
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tipo")
public class Tipo {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="tipo",length = 100,nullable=false)
    private String tipo;

    public Tipo() {
    }

    public Tipo(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }
    
    

}
