package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FireResidentDTO;
import com.safetynet.alerts.dto.FireResponseDTO;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.utils.AgeUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FireService {
    private static final Logger log = LoggerFactory.getLogger(FireService.class);
    private final DataRepository repository;

    public FireService(DataRepository repository) {
        this.repository = repository;
    }

    public FireResponseDTO getFireInfo(String address) {
        log.info("Service : récupération des infos pour l’adresse {}", address);

        Optional<String> station = repository.getFirestations().stream()
                .filter(f -> f.getAddress().equalsIgnoreCase(address))
                .map(f -> f.getStation())
                .findFirst();

        List<Person> personsAtAddress = repository.getPersons().stream()
                .filter(p -> p.getAddress().equalsIgnoreCase(address))
                .collect(Collectors.toList());

        List<FireResidentDTO> residents = personsAtAddress.stream()
                .map(p -> {
                    int age = AgeUtils.calculateAge(p.getFirstName(), p.getLastName(), repository.getMedicalRecords());
                    MedicalRecord record = repository.getMedicalRecords().stream()
                            .filter(mr -> mr.getFirstName().equals(p.getFirstName()) && mr.getLastName().equals(p.getLastName()))
                            .findFirst().orElse(new MedicalRecord());
                    return new FireResidentDTO(p.getFirstName(), p.getLastName(), p.getPhone(), age, record.getMedications(), record.getAllergies());
                }).collect(Collectors.toList());

        log.debug("Nombre de personnes à l’adresse {} : {}", address, residents.size());

        return new FireResponseDTO(residents, station.orElse("N/A"));
    }
}
