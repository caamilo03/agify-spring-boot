package com.example.agify.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.agify.model.AgifyResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgifyControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetAgeEndpoint() throws Exception {
        // Dado
        String nombre = "Maria";
        String url = "http://localhost:" + port + "/api/age/" + nombre;

        // Cuando
        ResponseEntity<AgifyResponse> response = restTemplate.getForEntity(url, AgifyResponse.class);

        // Entonces
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status debe ser 200 OK");

        AgifyResponse body = response.getBody();
        assertNotNull(body, "El cuerpo de la respuesta no debe ser null");
        assertEquals(nombre, body.getName(), "El nombre debe coincidir");
    }
}