package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonInfoDTO;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonInfoServiceTest {

    private PersonInfoService service;
    private DataRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DataRepository();
        repository.setPersons(List.of(
                new Person("John", "Doe", "1509 Culver St", "Culver", "97451", "123-456", "john@email.com"),
                new Person("Jane", "Doe", "1509 Culver St", "Culver", "97451", "123-457", "jane@email.com")
        ));
        repository.setMedicalRecords(List.of(
                new MedicalRecord("John", "Doe", "01/01/1984", List.of("med1"), List.of("all1")),
                new MedicalRecord("Jane", "Doe", "01/01/1986", List.of("med2"), List.of("all2"))
        ));
        service = new PersonInfoService(repository);
    }

    @Test
    void testGetPersonsByLastName() {
        List<PersonInfoDTO> list = service.getPersonsByLastName("Doe");
        assertEquals(2, list.size());
        assertEquals("John", list.get(0).getFirstName());
    }

    @Test
    void testNoMatch() {
        List<PersonInfoDTO> list = service.getPersonsByLastName("Smith");
        assertTrue(list.isEmpty());
    }
}
