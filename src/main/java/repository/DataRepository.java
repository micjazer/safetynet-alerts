package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.*;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Repository
public class DataRepository {
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<MedicalRecord> medicalRecords;

    @PostConstruct
    public void init() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream("data.json");
        JsonNode root = mapper.readTree(is);
        persons = Arrays.asList(mapper.treeToValue(root.get("persons"), Person[].class));
        firestations = Arrays.asList(mapper.treeToValue(root.get("firestations"), Firestation[].class));
        medicalRecords = Arrays.asList(mapper.treeToValue(root.get("medicalrecords"), MedicalRecord[].class));
    }

    public List<Person> getPersons() { return persons; }
    public List<Firestation> getFirestations() { return firestations; }
    public List<MedicalRecord> getMedicalRecords() { return medicalRecords; }
}
