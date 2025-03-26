package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.*;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.utils.AgeUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildAlertService {
    private static final Logger log = LoggerFactory.getLogger(ChildAlertService.class);
    private final DataRepository repository;

    public ChildAlertService(DataRepository repository) {
        this.repository = repository;
    }

    public ChildAlertDTO getChildrenByAddress(String address) {
        log.info("Service : recherche des enfants à l’adresse {}", address);

        List<Person> personsAtAddress = repository.getPersons().stream()
                .filter(p -> p.getAddress().equalsIgnoreCase(address))
                .collect(Collectors.toList());

        List<ChildWithAgeDTO> children = personsAtAddress.stream()
                .map(p -> {
                    int age = AgeUtils.calculateAge(p.getFirstName(), p.getLastName(), repository.getMedicalRecords());
                    return new ChildWithAgeDTO(p.getFirstName(), p.getLastName(), age);
                })
                .filter(child -> child.getAge() <= 18)
                .collect(Collectors.toList());

        List<String> otherHouseholdMembers = personsAtAddress.stream()
                .map(p -> p.getFirstName() + " " + p.getLastName())
                .filter(name -> children.stream().noneMatch(c -> (c.getFirstName() + " " + c.getLastName()).equals(name)))
                .collect(Collectors.toList());

        log.debug("Nombre total de membres à l’adresse : {}", personsAtAddress.size());
        log.debug("Nombre d'enfants : {}, autres membres : {}", children.size(), otherHouseholdMembers.size());

        return new ChildAlertDTO(children, otherHouseholdMembers);
    }
}

