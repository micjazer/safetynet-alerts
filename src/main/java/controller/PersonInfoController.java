package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.PersonInfoDTO;
import com.safetynet.alerts.service.PersonInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personInfo")
public class PersonInfoController {
    private static final Logger log = LoggerFactory.getLogger(PersonInfoController.class);
    private final PersonInfoService service;

    public PersonInfoController(PersonInfoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PersonInfoDTO>> getPersonsByLastName(@RequestParam String lastName) {
        log.info("Requête GET /personInfo reçue avec lastName={}", lastName);
        try {
            List<PersonInfoDTO> response = service.getPersonsByLastName(lastName);
            log.debug("Nombre de personnes trouvées avec le nom {} : {}", lastName, response.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Erreur lors du traitement de /personInfo : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
