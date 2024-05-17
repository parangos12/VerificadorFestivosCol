package com.udea.festivos.apirest_festivos.core.interfaces.servicios;

import java.util.List;

import com.udea.festivos.apirest_festivos.core.entidades.dto.FestivoDto;

public interface IFestivoServicio {
    
    Object verificarFestivo(String año,String mes,String dia);

    List<FestivoDto> obtenerFestivosAño(String año);
}
