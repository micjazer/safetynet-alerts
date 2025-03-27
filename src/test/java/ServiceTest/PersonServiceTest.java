package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    private PersonService service;
    private DataRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DataRepository();
        repository.setPersons(List.of(new Person("John", "Doe", "Address", "City", "Zip", "Phone", "Email")));
        service = new PersonService(repository);
    }

    @Test
    void testAddPerson() {
        Person p = new Person("Jane", "Smith", "Address", "City", "Zip", "123", "jane@email.com");
        service.addPerson(p);
        assertEquals(2, repository.getPersons().size());
    }

    @Test
    void testUpdatePerson() {
        Person updated = new Person("John", "Doe", "New Address", "City", "Zip", "Phone", "Email");
        boolean result = service.updatePerson(updated);
        assertTrue(result);
    }

    @Test
    void testDeletePerson() {
        boolean result = service.deletePerson("John", "Doe");
        assertTrue(result);
        assertTrue(repository.getPersons().isEmpty());
    }
}

