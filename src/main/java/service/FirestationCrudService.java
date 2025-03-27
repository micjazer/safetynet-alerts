package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class FirestationCrudService {
    private static final Logger log = LoggerFactory.getLogger(FirestationCrudService.class);
    private final DataRepository repository;

    public FirestationCrudService(DataRepository repository) {
        this.repository = repository;
    }

    public void addFirestation(Firestation fs) {
        log.debug("Ajout mapping caserne : {}", fs.getAddress());
        repository.getFirestations().add(fs);
    }

    public boolean updateFirestation(Firestation updatedFs) {
        List<Firestation> firestations = repository.getFirestations();
        for (int i = 0; i < firestations.size(); i++) {
            Firestation fs = firestations.get(i);
            if (fs.getAddress().equalsIgnoreCase(updatedFs.getAddress())) {
                firestations.set(i, updatedFs);
                log.debug("Mapping mis à jour pour l’adresse : {}", updatedFs.getAddress());
                return true;
            }
        }
        log.debug("Mapping introuvable pour mise à jour : {}", updatedFs.getAddress());
        return false;
    }

    public boolean deleteFirestation(String address) {
        Iterator<Firestation> iterator = repository.getFirestations().iterator();
        while (iterator.hasNext()) {
            Firestation fs = iterator.next();
            if (fs.getAddress().equalsIgnoreCase(address)) {
                iterator.remove();
                log.debug("Mapping supprimé pour l’adresse : {}", address);
                return true;
            }
        }
        log.debug("Mapping introuvable pour suppression : {}", address);
        return false;
    }
}

