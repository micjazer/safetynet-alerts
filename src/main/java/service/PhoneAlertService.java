package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PhoneAlertService {
    private static final Logger log = LoggerFactory.getLogger(PhoneAlertService.class);
    private final DataRepository repository;

    public PhoneAlertService(DataRepository repository) {
        this.repository = repository;
    }

    public List<String> getPhonesByStation(int stationNumber) {
        log.info("Service : recherche des téléphones pour la caserne {}", stationNumber);

        Set<String> addresses = repository.getFirestations().stream()
                .filter(fs -> fs.getStation().equals(String.valueOf(stationNumber)))
                .map(Firestation::getAddress)
                .collect(Collectors.toSet());

        log.debug("Adresses desservies par la caserne {} : {}", stationNumber, addresses);

        List<String> phones = repository.getPersons().stream()
                .filter(p -> addresses.contains(p.getAddress()))
                .map(Person::getPhone)
                .distinct()
                .collect(Collectors.toList());

        log.info("Nombre de téléphones collectés : {}", phones.size());
        return phones;
    }
}