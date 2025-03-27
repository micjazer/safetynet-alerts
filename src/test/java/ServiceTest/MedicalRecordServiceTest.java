package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordServiceTest {

    private MedicalRecordService service;
    private DataRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DataRepository();
        repository.setMedicalRecords(List.of(new MedicalRecord("John", "Doe", "01/01/1980", List.of("med1"), List.of("all1"))));
        service = new MedicalRecordService(repository);
    }

    @Test
    void testAddMedicalRecord() {
        MedicalRecord mr = new MedicalRecord("Jane", "Doe", "02/02/1990", List.of(), List.of());
        service.addMedicalRecord(mr);
        assertEquals(2, repository.getMedicalRecords().size());
    }

    @Test
    void testUpdateMedicalRecord() {
        MedicalRecord updated = new MedicalRecord("John", "Doe", "01/01/1980", List.of("med2"), List.of("all2"));
        boolean result = service.updateMedicalRecord(updated);
        assertTrue(result);
    }

    @Test
    void testDeleteMedicalRecord() {
        boolean result = service.deleteMedicalRecord("John", "Doe");
        assertTrue(result);
    }
}
