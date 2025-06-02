package com.example.agify.controller;

import com.example.agify.model.AgifyResponse;
import com.example.agify.service.AgifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AgifyController {

    private final AgifyService agifyService;

    // Endpoint principal: GET /api/age/{nombre}
    @GetMapping("/age/{name}")
    public ResponseEntity<AgifyResponse> getAge(@PathVariable String name) {
        log.info("Petición recibida para nombre: {}", name);

        // Validar que el nombre no esté vacío
        if (name == null || name.trim().isEmpty()) {
            log.warn("Nombre vacío recibido");
            return ResponseEntity.badRequest().build();
        }

        // Llamar al servicio
        AgifyResponse response = agifyService.getAgeByName(name.trim());

        // Verificar si se encontró información
        if (response == null || response.getAge() == null) {
            log.warn("No se encontró información para: {}", name);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    // Endpoint alternativo con query parameter: GET /api/age?name=Juan
    @GetMapping("/age")
    public ResponseEntity<AgifyResponse> getAgeByQuery(@RequestParam String name) {
        return getAge(name);
    }
}