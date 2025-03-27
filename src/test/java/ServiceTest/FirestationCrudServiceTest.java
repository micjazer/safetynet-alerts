package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FirestationCrudServiceTest {

    private FirestationCrudService service;
    private DataRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DataRepository();
        repository.setFirestations(List.of(new Firestation("1509 Culver St", "3")));
        service = new FirestationCrudService(repository);
    }

    @Test
    void testAddFirestation() {
        Firestation fs = new Firestation("29 15th St", "2");
        service.addFirestation(fs);
        assertEquals(2, repository.getFirestations().size());
    }

    @Test
    void testUpdateFirestation() {
        Firestation updated = new Firestation("1509 Culver St", "4");
        boolean result = service.updateFirestation(updated);
        assertTrue(result);
    }

    @Test
    void testDeleteFirestation() {
        boolean result = service.deleteFirestation("1509 Culver St");
        assertTrue(result);
    }
}
