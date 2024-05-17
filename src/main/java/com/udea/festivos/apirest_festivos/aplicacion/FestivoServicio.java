package com.udea.festivos.apirest_festivos.aplicacion;


import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.udea.festivos.apirest_festivos.core.entidades.dto.FestivoDto;
import com.udea.festivos.apirest_festivos.core.interfaces.repositorios.IFestivosRepositorio;
import com.udea.festivos.apirest_festivos.core.interfaces.servicios.IFestivoServicio;

@Service
public class FestivoServicio implements IFestivoServicio{

    private final IFestivosRepositorio festivoRepositorio;
    private static final Pattern REGEX_FECHA =  Pattern.compile("^\\d{4}/\\d{1,2}/\\d{1,2}$");
    private static final Pattern REGEX_AÑO =  Pattern.compile("^\\d{4}$");

    public FestivoServicio(IFestivosRepositorio festivoRepositorio){
        this.festivoRepositorio=festivoRepositorio;
    }

    @Override
    public String verificarFestivo(String año, String mes, String dia) {
        String mensaje="La fecha "+año+"/"+mes+"/"+dia+" ";
        if (!validarFecha(año, mes, dia)){
            mensaje+= "No es valida";}
        else{
            Integer añoInt = Integer.parseInt(año);
            Integer mesInt = Integer.parseInt(mes);
            Integer diaInt = Integer.parseInt(dia);    
            Object posibleFestivo=festivoRepositorio.verificarFestivo(añoInt, mesInt, diaInt);
            if(posibleFestivo==null){
                mensaje+= "no es festivo";
            }else{
                mensaje+="es festivo y se celebra: "+posibleFestivo;
            }
        }
        return mensaje;
    }

    @Override
    public List<FestivoDto> obtenerFestivosAño(String año) {
        if(REGEX_AÑO.matcher(año).matches()){
            Integer añoInt = Integer.parseInt(año);
            List<FestivoDto> festivos=festivoRepositorio.obtenerFestivosAño(añoInt);
            return festivos;}
        return null;
    }

    public boolean validarFecha(String año, String mes, String dia ){
        String fechaString = año + "/" + mes + "/" + dia;
        if (!REGEX_FECHA.matcher(fechaString).matches()) {
            return false;
        }
        Integer añoInt = Integer.parseInt(año);
        Integer mesInt = Integer.parseInt(mes);
        Integer diaInt = Integer.parseInt(dia);

        if (añoInt < 0 || mesInt <= 0 || mesInt > 12 || diaInt <= 0 || diaInt > 31) {
            return false;
        }

        if (mesInt == 2) {
            if (añoInt % 4 == 0 && (añoInt % 100 != 0 || añoInt % 400 == 0)) {
                return diaInt <= 29;
            } else {
                return diaInt <= 28;
            }
        }

        if (mesInt == 4 || mesInt == 6 || mesInt == 9 || mesInt == 11) {
            return diaInt <= 30;
        }

        return true;
    }

}
    

