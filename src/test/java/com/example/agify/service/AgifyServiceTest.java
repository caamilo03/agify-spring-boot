package com.example.agify.service;

import com.example.agify.model.AgifyResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AgifyServiceTest {

    private AgifyService agifyService;

    @BeforeEach
    void setUp() {
        agifyService = new AgifyService();
    }

    @Test
    void testGetAgeByName() {
        // Dado
        String nombre = "Maria";

        // Cuando
        AgifyResponse resultado = agifyService.getAgeByName(nombre);

        // Entonces
        assertNotNull(resultado, "La respuesta no debe ser null");
        assertEquals(nombre, resultado.getName(), "El nombre debe coincidir");
        assertNotNull(resultado.getAge(), "La edad no debe ser null");
        assertTrue(resultado.getAge() > 0, "La edad debe ser mayor a 0");
    }

    @Test
    void testGetAgeByNameWithEmptyString() {
        // Dado
        String nombreVacio = "";

        // Cuando
        AgifyResponse resultado = agifyService.getAgeByName(nombreVacio);

        // Entonces
        if (resultado != null) {
            assertNull(resultado.getAge(), "La edad debe ser null para nombre vacío");
        }
    }

    @Test
    void testGetAgeByNameWithCommonNames() {
        // Test con nombres comunes
        String[] nombresComunes = {"Juan", "Ana", "Carlos"};

        for (String nombre : nombresComunes) {
            AgifyResponse resultado = agifyService.getAgeByName(nombre);

            assertNotNull(resultado, "Resultado no debe ser null para: " + nombre);
            assertEquals(nombre, resultado.getName(), "Nombre debe coincidir para: " + nombre);
        }
    }
}