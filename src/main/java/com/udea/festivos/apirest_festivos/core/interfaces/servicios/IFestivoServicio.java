package com.udea.festivos.apirest_festivos.core.interfaces.servicios;

/**
 * @author Pedro Arango Sánchez
 * @author David Andrés Montoya Castaño
 * Universidad de Antioquia
 * Técnicas de Programación y Laboratorio [2554307] 
 * Grupo: 01 | Semestre: 2024-1
 */

import java.util.List;

import com.udea.festivos.apirest_festivos.core.entidades.dto.FestivoDto;

public interface IFestivoServicio {
    
    Object verificarFestivo(String año,String mes,String dia);

    List<FestivoDto> obtenerFestivosAño(String año);
}
