package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.FirestationResponseDTO;
import com.safetynet.alerts.service.FirestationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
public class FirestationController {
    private static final Logger log = LoggerFactory.getLogger(FirestationController.class);
    private final FirestationService service;

    public FirestationController(FirestationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<FirestationResponseDTO> getByStation(@RequestParam int stationNumber) {
        log.info("Requête GET /firestation reçue avec stationNumber={}", stationNumber);
        try {
            FirestationResponseDTO response = service.getPersonsByStation(stationNumber);
            log.debug("Réponse construite : {} personnes trouvées", response.getPersons().size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Erreur lors du traitement de /firestation : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
