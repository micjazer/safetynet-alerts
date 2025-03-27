package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    private static final Logger log = LoggerFactory.getLogger(MedicalRecordController.class);
    private final MedicalRecordService service;

    public MedicalRecordController(MedicalRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> addMedicalRecord(@RequestBody MedicalRecord record) {
        log.info("POST /medicalRecord - Ajout dossier : {} {}", record.getFirstName(), record.getLastName());
        try {
            service.addMedicalRecord(record);
            return ResponseEntity.ok("Medical record added.");
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error adding medical record.");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateMedicalRecord(@RequestBody MedicalRecord record) {
        log.info("PUT /medicalRecord - Mise à jour dossier : {} {}", record.getFirstName(), record.getLastName());
        try {
            boolean updated = service.updateMedicalRecord(record);
            return updated ? ResponseEntity.ok("Medical record updated.") :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error updating medical record.");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        log.info("DELETE /medicalRecord - Suppression du dossier : {} {}", firstName, lastName);
        try {
            boolean deleted = service.deleteMedicalRecord(firstName, lastName);
            return deleted ? ResponseEntity.ok("Medical record deleted.") :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Erreur lors de la suppression : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error deleting medical record.");
        }
    }
}

