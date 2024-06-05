package com.udea.festivos.apirest_festivos.aplicacion;

/**
 * @author Pedro Arango Sánchez
 * @author Cristian Felipe Estrada Calvo
 * @author David Andrés Montoya Castaño
 * Universidad de Antioquia
 * Técnicas de Programación y Laboratorio [2554307] 
 * Grupo: 01 | Semestre: 2024-1
 */

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.core.io.Resource;

import jakarta.annotation.PostConstruct;

@Component
public class InicializadorFunciones {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void initialize() {
        executeSQLScript("classpath:functions.sql");
    }

    private void executeSQLScript(String scriptPath) {
        try {
            Resource resource = resourceLoader.getResource(scriptPath);
            String sql = new String(Files.readAllBytes(resource.getFile().toPath()), Charset.defaultCharset());
            jdbcTemplate.execute(sql);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
