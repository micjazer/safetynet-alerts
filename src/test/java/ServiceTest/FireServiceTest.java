package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FireResponseDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FireServiceTest {

    private FireService service;
    private DataRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DataRepository();
        repository.setFirestations(List.of(new Firestation("1509 Culver St", "3")));
        repository.setPersons(List.of(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "john.boyd@email.com")));
        repository.setMedicalRecords(List.of(new MedicalRecord("John", "Boyd", "03/06/1984", List.of("med1"), List.of("all1"))));
        service = new FireService(repository);
    }

    @Test
    void testGetFireInfo() {
        FireResponseDTO dto = service.getFireInfo("1509 Culver St");
        assertEquals("3", dto.getStation());
        assertEquals(1, dto.getResidents().size());
    }

    @Test
    void testNoFirestationMapping() {
        FireResponseDTO dto = service.getFireInfo("Unknown Address");
        assertEquals("N/A", dto.getStation());
        assertTrue(dto.getResidents().isEmpty());
    }
}

