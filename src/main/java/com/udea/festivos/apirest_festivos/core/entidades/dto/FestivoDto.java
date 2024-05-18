package com.udea.festivos.apirest_festivos.core.entidades.dto;

/**
 * @author Pedro Arango Sánchez
 * @author David Andrés Montoya Castaño
 * Universidad de Antioquia
 * Técnicas de Programación y Laboratorio [2554307] 
 * Grupo: 01 | Semestre: 2024-1
 */

import java.time.LocalDate;

public interface FestivoDto {
    
     String getFestivo();
     LocalDate getFecha();

}