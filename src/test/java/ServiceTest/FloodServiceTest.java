package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FloodResponseDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FloodServiceTest {

    private FloodService service;
    private DataRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DataRepository();
        repository.setFirestations(List.of(new Firestation("29 15th St", "2")));
        repository.setPersons(List.of(new Person("Jane", "Doe", "29 15th St", "Culver", "97451", "123-456", "jane@email.com")));
        repository.setMedicalRecords(List.of(new MedicalRecord("Jane", "Doe", "01/01/2010", List.of("med1"), List.of("all1"))));
        service = new FloodService(repository);
    }

    @Test
    void testGetFloodInfo() {
        Map<String, List<FloodResponseDTO>> map = service.getFloodInfo(List.of(2));
        assertTrue(map.containsKey("29 15th St"));
        assertEquals(1, map.get("29 15th St").size());
    }

    @Test
    void testNoStationsFound() {
        Map<String, List<FloodResponseDTO>> map = service.getFloodInfo(List.of(99));
        assertTrue(map.isEmpty());
    }
}

