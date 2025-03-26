package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.*;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.utils.AgeUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FirestationService {
    private final DataRepository repository;

    public FirestationService(DataRepository repository) {
        this.repository = repository;
    }

    public FirestationResponseDTO getPersonsByStation(int stationNumber) {
        List<String> addresses = repository.getFirestations().stream()
                .filter(fs -> Integer.parseInt(fs.getStation()) == stationNumber)
                .map(Firestation::getAddress)
                .collect(Collectors.toList());

        List<Person> matchedPersons = repository.getPersons().stream()
                .filter(p -> addresses.contains(p.getAddress()))
                .collect(Collectors.toList());

        int adults = 0, children = 0;
        List<PersonSummaryDTO> summaries = new ArrayList<>();

        for (Person p : matchedPersons) {
            int age = AgeUtils.calculateAge(p.getFirstName(), p.getLastName(), repository.getMedicalRecords());
            if (age <= 18) children++; else adults++;
            summaries.add(new PersonSummaryDTO(p.getFirstName(), p.getLastName(), p.getAddress(), p.getPhone()));
        }

        return new FirestationResponseDTO(summaries, adults, children);
    }
}
