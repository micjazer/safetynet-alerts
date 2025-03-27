package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FirestationResponseDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FirestationServiceTest {

    private FirestationService service;
    private DataRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DataRepository();
        repository.setFirestations(List.of(new Firestation("1509 Culver St", "3")));
        repository.setPersons(List.of(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "john.boyd@email.com")));
        repository.setMedicalRecords(List.of(new MedicalRecord("John", "Boyd", "03/06/1984", List.of("med1"), List.of("all1"))));
        service = new FirestationService(repository);
    }

    @Test
    void testGetPersonsByStation() {
        FirestationResponseDTO dto = service.getPersonsByStation(3);
        assertEquals(1, dto.getPersons().size());
        assertEquals(1, dto.getAdultCount());
        assertEquals(0, dto.getChildCount());
    }

    @Test
    void testGetPersonsByStationEmpty() {
        FirestationResponseDTO dto = service.getPersonsByStation(5);
        assertEquals(0, dto.getPersons().size());
    }
}

