package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FloodResponseDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.utils.AgeUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FloodService {
    private static final Logger log = LoggerFactory.getLogger(FloodService.class);
    private final DataRepository repository;

    public FloodService(DataRepository repository) {
        this.repository = repository;
    }

    public Map<String, List<FloodResponseDTO>> getFloodInfo(List<Integer> stationNumbers) {
        log.info("Service : récupération des foyers pour les stations {}", stationNumbers);

        Set<String> addresses = repository.getFirestations().stream()
                .filter(fs -> stationNumbers.contains(Integer.parseInt(fs.getStation())))
                .map(Firestation::getAddress)
                .collect(Collectors.toSet());

        log.debug("Adresses concernées : {}", addresses);

        Map<String, List<FloodResponseDTO>> result = new HashMap<>();
        for (String address : addresses) {
            List<FloodResponseDTO> residents = repository.getPersons().stream()
                    .filter(p -> p.getAddress().equalsIgnoreCase(address))
                    .map(p -> {
                        int age = AgeUtils.calculateAge(p.getFirstName(), p.getLastName(), repository.getMedicalRecords());
                        MedicalRecord mr = repository.getMedicalRecords().stream()
                                .filter(r -> r.getFirstName().equals(p.getFirstName()) && r.getLastName().equals(p.getLastName()))
                                .findFirst().orElse(new MedicalRecord());
                        return new FloodResponseDTO(p.getFirstName(), p.getLastName(), p.getPhone(), age, mr.getMedications(), mr.getAllergies());
                    }).collect(Collectors.toList());
            result.put(address, residents);
        }

        log.info("Nombre total d’adresses traitées : {}", result.size());
        return result;
    }
}

