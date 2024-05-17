package com.udea.festivos.apirest_festivos.presentacion;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea.festivos.apirest_festivos.core.interfaces.servicios.IFestivoServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/festivos/")
public class FestivoControlador {
    
    private final IFestivoServicio festivoServicio;

    public FestivoControlador(IFestivoServicio iFestivoServicio){
        this.festivoServicio=iFestivoServicio;
    }

    @GetMapping("verificar/{año}/{mes}/{dia}")
    public ResponseEntity<Object> verificarVestivo(@PathVariable String año,@PathVariable String mes,@PathVariable String dia) {
        Object mensaje=festivoServicio.verificarFestivo(año, mes, dia);
        if (mensaje.equals("La fecha "+año+"/"+mes+"/"+dia+" "+"No es valida")){
            return ResponseEntity.badRequest().body(mensaje);
        }
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("obtener/{año}")
    public ResponseEntity<?> obtenerFestivosAño(@PathVariable String año) {
        if(festivoServicio.obtenerFestivosAño(año)!=null){
            return ResponseEntity.ok(festivoServicio.obtenerFestivosAño(año));
        }
        return ResponseEntity.badRequest().body("No se encontraron festivos para el año "+año+" o el año no es valido");
    }

}
