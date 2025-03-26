package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.ChildAlertDTO;
import com.safetynet.alerts.service.ChildAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {
    private static final Logger log = LoggerFactory.getLogger(ChildAlertController.class);
    private final ChildAlertService service;

    public ChildAlertController(ChildAlertService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ChildAlertDTO> getChildrenByAddress(@RequestParam String address) {
        log.info("Requête GET /childAlert reçue avec address={}", address);
        try {
            ChildAlertDTO response = service.getChildrenByAddress(address);
            log.debug("Nombre d'enfants trouvés : {}", response.getChildren().size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Erreur lors du traitement de /childAlert : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
