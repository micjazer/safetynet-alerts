package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.PhoneAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {
    private static final Logger log = LoggerFactory.getLogger(PhoneAlertController.class);
    private final PhoneAlertService service;

    public PhoneAlertController(PhoneAlertService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<String>> getPhonesByStation(@RequestParam int firestation) {
        log.info("Requête GET /phoneAlert reçue avec firestation={}", firestation);
        try {
            List<String> phones = service.getPhonesByStation(firestation);
            log.debug("Nombre de téléphones trouvés : {}", phones.size());
            return ResponseEntity.ok(phones);
        } catch (Exception e) {
            log.error("Erreur lors du traitement de /phoneAlert : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}