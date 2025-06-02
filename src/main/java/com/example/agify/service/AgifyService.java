package com.example.agify.service;

import com.example.agify.model.AgifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class AgifyService {

    private final RestTemplate restTemplate;
    private static final String AGIFY_URL = "https://api.agify.io/?name=";

    public AgifyService() {
        this.restTemplate = new RestTemplate();
    }

    public AgifyResponse getAgeByName(String name) {
        try {
            log.info("Consultando edad para: {}", name);

            String url = AGIFY_URL + name;
            AgifyResponse response = restTemplate.getForObject(url, AgifyResponse.class);

            if (response != null && response.getAge() != null) {
                log.info("Edad encontrada para {}: {} años", name, response.getAge());
            } else {
                log.warn("No se encontró edad para: {}", name);
            }

            return response;

        } catch (Exception e) {
            log.error("Error al consultar API para {}: {}", name, e.getMessage());
            return null;
        }
    }
}