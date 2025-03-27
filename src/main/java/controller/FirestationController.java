package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.FirestationCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
public class FirestationController {
    private static final Logger log = LoggerFactory.getLogger(FirestationController.class);
    private final FirestationCrudService service;

    public FirestationController(FirestationCrudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> addFirestation(@RequestBody Firestation fs) {
        log.info("POST /firestation - Ajout mapping : {} => Station {}", fs.getAddress(), fs.getStation());
        try {
            service.addFirestation(fs);
            return ResponseEntity.ok("Firestation mapping added.");
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout du mapping : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error adding firestation mapping.");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateFirestation(@RequestBody Firestation fs) {
        log.info("PUT /firestation - Mise à jour mapping : {} => Station {}", fs.getAddress(), fs.getStation());
        try {
            boolean updated = service.updateFirestation(fs);
            return updated ? ResponseEntity.ok("Mapping updated.") :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour du mapping : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error updating firestation mapping.");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFirestation(@RequestParam String address) {
        log.info("DELETE /firestation - Suppression du mapping pour l’adresse {}", address);
        try {
            boolean deleted = service.deleteFirestation(address);
            return deleted ? ResponseEntity.ok("Mapping deleted.") :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Erreur lors de la suppression du mapping : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error deleting firestation mapping.");
        }
    }
}
