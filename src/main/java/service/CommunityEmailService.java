package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityEmailService {
    private static final Logger log = LoggerFactory.getLogger(CommunityEmailService.class);
    private final DataRepository repository;

    public CommunityEmailService(DataRepository repository) {
        this.repository = repository;
    }

    public List<String> getEmailsByCity(String city) {
        log.info("Service : récupération des emails pour la ville {}", city);
        return repository.getPersons().stream()
                .filter(p -> p.getCity().equalsIgnoreCase(city))
                .map(Person::getEmail)
                .distinct()
                .collect(Collectors.toList());
    }
}

