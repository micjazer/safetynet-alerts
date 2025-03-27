package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonInfoDTO;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.utils.AgeUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonInfoService {
    private static final Logger log = LoggerFactory.getLogger(PersonInfoService.class);
    private final DataRepository repository;

    public PersonInfoService(DataRepository repository) {
        this.repository = repository;
    }

    public List<PersonInfoDTO> getPersonsByLastName(String lastName) {
        log.info("Service : récupération des infos pour le nom {}", lastName);

        return repository.getPersons().stream()
                .filter(p -> p.getLastName().equalsIgnoreCase(lastName))
                .map(p -> {
                    int age = AgeUtils.calculateAge(p.getFirstName(), p.getLastName(), repository.getMedicalRecords());
                    MedicalRecord mr = repository.getMedicalRecords().stream()
                            .filter(r -> r.getFirstName().equals(p.getFirstName()) && r.getLastName().equals(p.getLastName()))
                            .findFirst().orElse(new MedicalRecord());
                    return new PersonInfoDTO(p.getFirstName(), p.getLastName(), p.getAddress(), age, p.getEmail(), mr.getMedications(), mr.getAllergies());
                })
                .collect(Collectors.toList());
    }
}

