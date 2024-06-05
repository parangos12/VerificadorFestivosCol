package com.udea.festivos.apirest_festivos.core.interfaces.repositorios;

/**
 * @author Pedro Arango Sánchez
 * @author Cristian Felipe Estrada Calvo
 * @author David Andrés Montoya Castaño
 * Universidad de Antioquia
 * Técnicas de Programación y Laboratorio [2554307] 
 * Grupo: 01 | Semestre: 2024-1
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udea.festivos.apirest_festivos.core.entidades.Festivo;
import com.udea.festivos.apirest_festivos.core.entidades.dto.FestivoDto;

@Repository
public interface IFestivosRepositorio extends JpaRepository<Festivo, Integer> {
    
    @Query(value = """
        
        SELECT nombre FROM (
        SELECT nombre,
        CASE
            WHEN idtipo=1 THEN TO_DATE(?1 || '-' || mes || '-' || dia, 'YYYY-MM-DD')
            WHEN idtipo=2 AND dia_semana(dia, mes, ?1)<>0 THEN (TO_DATE(?1 || '-' || mes || '-' || dia, 'YYYY-MM-DD') + (7 - dia_semana(dia, mes, ?1)))
            WHEN idtipo=2 AND dia_semana(dia, mes, ?1)=0 THEN (TO_DATE(?1 || '-' || mes || '-' || dia, 'YYYY-MM-DD'))
            WHEN idtipo=3 THEN domingo_pascua(?1)+DiasPascua
            WHEN idtipo=4 THEN 
                CASE 
                    WHEN dia_semana(CAST(EXTRACT(DAY FROM domingo_pascua(?1)+DiasPascua) AS INTEGER), CAST(EXTRACT(MONTH FROM domingo_pascua(?1)+DiasPascua) AS INTEGER), ?1)=0
                        THEN domingo_pascua(?1)+DiasPascua
                    ELSE 
                        domingo_pascua(?1)+DiasPascua + (7-dia_semana(CAST(EXTRACT(DAY FROM domingo_pascua(?1)+DiasPascua) AS INTEGER), CAST(EXTRACT(MONTH FROM domingo_pascua(?1)+DiasPascua) AS INTEGER), ?1))
                END
        END AS dia_festivo
        FROM 
            festivo) AS sub
        WHERE dia_festivo=TO_DATE(?1 || '-' || ?2 || '-' || ?3, 'YYYY-MM-DD');
        """, nativeQuery = true)
    Object verificarFestivo(int año, int mes, int dia);

    @Query(value = """
        
    SELECT nombre as festivo,fecha FROM (
    SELECT nombre,
    CASE
        WHEN idtipo=1 THEN TO_DATE(?1 || '-' || mes || '-' || dia, 'YYYY-MM-DD')
        WHEN idtipo=2 AND dia_semana(dia, mes, ?1)<>0 THEN (TO_DATE(?1 || '-' || mes || '-' || dia, 'YYYY-MM-DD') + (7 - dia_semana(dia, mes, ?1)))
        WHEN idtipo=2 AND dia_semana(dia, mes, ?1)=0 THEN (TO_DATE(?1 || '-' || mes || '-' || dia, 'YYYY-MM-DD'))
        WHEN idtipo=3 THEN domingo_pascua(?1)+DiasPascua
        WHEN idtipo=4 THEN 
            CASE 
                WHEN dia_semana(CAST(EXTRACT(DAY FROM domingo_pascua(?1)+DiasPascua) AS INTEGER), CAST(EXTRACT(MONTH FROM domingo_pascua(?1)+DiasPascua) AS INTEGER), ?1)=0
                    THEN domingo_pascua(?1)+DiasPascua
                ELSE 
                    domingo_pascua(?1)+DiasPascua + (7-dia_semana(CAST(EXTRACT(DAY FROM domingo_pascua(?1)+DiasPascua) AS INTEGER), CAST(EXTRACT(MONTH FROM domingo_pascua(?1)+DiasPascua) AS INTEGER), ?1))
            END
    END AS fecha
    FROM 
        festivo) AS sub;
    """, nativeQuery = true)
    List<FestivoDto> obtenerFestivosAño(int año);

}