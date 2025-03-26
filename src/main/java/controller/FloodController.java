package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.FloodResponseDTO;
import com.safetynet.alerts.service.FloodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flood")
public class FloodController {
    private static final Logger log = LoggerFactory.getLogger(FloodController.class);
    private final FloodService service;

    public FloodController(FloodService service) {
        this.service = service;
    }

    @GetMapping("/stations")
    public ResponseEntity<Map<String, List<FloodResponseDTO>>> getFloodStations(@RequestParam List<Integer> stations) {
        log.info("Requête GET /flood/stations reçue avec stations={}", stations);
        try {
            Map<String, List<FloodResponseDTO>> response = service.getFloodInfo(stations);
            log.debug("Nombre d’adresses retournées : {}", response.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Erreur lors du traitement de /flood/stations : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}