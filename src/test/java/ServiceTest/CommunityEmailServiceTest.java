package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommunityEmailServiceTest {

    private CommunityEmailService service;
    private DataRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DataRepository();
        repository.setPersons(List.of(
                new Person("John", "Doe", "1509 Culver St", "Culver", "97451", "123-456", "john@email.com"),
                new Person("Jane", "Smith", "29 15th St", "Culver", "97451", "123-457", "jane@email.com"),
                new Person("Alan", "Brown", "10 Elm St", "Springfield", "97452", "123-458", "alan@email.com")
        ));
        service = new CommunityEmailService(repository);
    }

    @Test
    void testGetEmailsByCity() {
        List<String> emails = service.getEmailsByCity("Culver");
        assertEquals(2, emails.size());
    }

    @Test
    void testEmptyResult() {
        List<String> emails = service.getEmailsByCity("Nowhere");
        assertTrue(emails.isEmpty());
    }
}

