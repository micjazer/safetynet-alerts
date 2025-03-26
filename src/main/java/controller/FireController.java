package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.FireResponseDTO;
import com.safetynet.alerts.service.FireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fire")
public class FireController {
    private static final Logger log = LoggerFactory.getLogger(FireController.class);
    private final FireService service;

    public FireController(FireService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<FireResponseDTO> getFireInfo(@RequestParam String address) {
        log.info("Requête GET /fire reçue avec address={}", address);
        try {
            FireResponseDTO response = service.getFireInfo(address);
            log.debug("Nombre de résidents trouvés : {}", response.getResidents().size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Erreur lors du traitement de /fire : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
