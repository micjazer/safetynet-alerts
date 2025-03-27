package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        log.info("POST /person - Ajout d'une personne : {} {}", person.getFirstName(), person.getLastName());
        try {
            service.addPerson(person);
            return ResponseEntity.ok("Person added successfully.");
        } catch (Exception e) {
            log.error("Erreur lors de l'ajout : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error adding person.");
        }
    }

    @PutMapping
    public ResponseEntity<String> updatePerson(@RequestBody Person person) {
        log.info("PUT /person - Mise à jour de : {} {}", person.getFirstName(), person.getLastName());
        try {
            boolean updated = service.updatePerson(person);
            return updated ? ResponseEntity.ok("Person updated.") :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error updating person.");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        log.info("DELETE /person - Suppression de : {} {}", firstName, lastName);
        try {
            boolean deleted = service.deletePerson(firstName, lastName);
            return deleted ? ResponseEntity.ok("Person deleted.") :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Erreur lors de la suppression : {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error deleting person.");
        }
    }
}
