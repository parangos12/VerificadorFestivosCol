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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="festivo")
public class Festivo {
    
    @Id
    @Column(name="id")
    private int id;

    @Column(name="nombre",length = 100,nullable=false)
    private String nombre;

    @Column(name="dia",nullable=false)
    private int dia;

    @Column(name="mes",nullable=false)
    private int mes;
    
    @Column(name="diaspascua",nullable=false)
    private int diasPascua;

    @ManyToOne
    @JoinColumn(name="idtipo",referencedColumnName = "id",nullable=false)
    private Tipo tipo;

    public Festivo() {
    }

    public Festivo(int id, String nombre, int dia, int mes, int diasPascua, Tipo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.diasPascua = diasPascua;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getDiasPascua() {
        return diasPascua;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setDiasPascua(int diasPascua) {
        this.diasPascua = diasPascua;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    

    


}
