package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.CommunityEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {
    private static final Logger log = LoggerFactory.getLogger(CommunityEmailController.class);
    private final CommunityEmailService service;

    public CommunityEmailController(CommunityEmailService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<String>> getEmailsByCity(@RequestParam String city) {
        log.info("Requête GET /communityEmail reçue avec city={}", city);
        try {
            List<String> emails = service.getEmailsByCity(city);
            log.debug("Nombre d'emails trouvés pour la ville {} : {}", city, emails.size());
            return ResponseEntity.ok(emails);
        } catch (Exception e) {
            log.error("Erreur lors du traitement de /communityEmail : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

