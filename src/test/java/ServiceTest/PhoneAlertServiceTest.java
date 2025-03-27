package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhoneAlertServiceTest {

    private PhoneAlertService service;
    private DataRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DataRepository();
        repository.setFirestations(List.of(new Firestation("1509 Culver St", "3")));
        repository.setPersons(List.of(
                new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "john.boyd@email.com"),
                new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "jacob.boyd@email.com")
        ));
        service = new PhoneAlertService(repository);
    }

    @Test
    void testGetPhonesByStation() {
        List<String> phones = service.getPhonesByStation(3);
        assertEquals(2, phones.size());
    }

    @Test
    void testNoPhonesFound() {
        List<String> phones = service.getPhonesByStation(99);
        assertTrue(phones.isEmpty());
    }
}
