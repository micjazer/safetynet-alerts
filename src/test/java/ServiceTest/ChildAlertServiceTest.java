package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.ChildAlertDTO;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChildAlertServiceTest {

    private ChildAlertService service;
    private DataRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DataRepository();
        repository.setPersons(List.of(
                new Person("Jane", "Doe", "29 15th St", "Culver", "97451", "841-874-6512", "jane.doe@email.com"),
                new Person("John", "Doe", "29 15th St", "Culver", "97451", "841-874-6512", "john.doe@email.com")
        ));
        repository.setMedicalRecords(List.of(
                new MedicalRecord("Jane", "Doe", "01/01/2017", List.of(), List.of()),
                new MedicalRecord("John", "Doe", "01/01/1980", List.of(), List.of())
        ));
        service = new ChildAlertService(repository);
    }

    @Test
    void testGetChildrenByAddress() {
        ChildAlertDTO dto = service.getChildrenByAddress("29 15th St");
        assertEquals(1, dto.getChildren().size());
        assertEquals(1, dto.getOtherHouseholdMembers().size());
    }

    @Test
    void testNoChildrenAtAddress() {
        ChildAlertDTO dto = service.getChildrenByAddress("Nonexistent");
        assertTrue(dto.getChildren().isEmpty());
    }
}

