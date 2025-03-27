package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class MedicalRecordService {
    private static final Logger log = LoggerFactory.getLogger(MedicalRecordService.class);
    private final DataRepository repository;

    public MedicalRecordService(DataRepository repository) {
        this.repository = repository;
    }

    public void addMedicalRecord(MedicalRecord record) {
        log.debug("Ajout du dossier médical pour : {} {}", record.getFirstName(), record.getLastName());
        repository.getMedicalRecords().add(record);
    }

    public boolean updateMedicalRecord(MedicalRecord updatedRecord) {
        List<MedicalRecord> records = repository.getMedicalRecords();
        for (int i = 0; i < records.size(); i++) {
            MedicalRecord r = records.get(i);
            if (r.getFirstName().equals(updatedRecord.getFirstName()) &&
                    r.getLastName().equals(updatedRecord.getLastName())) {
                records.set(i, updatedRecord);
                log.debug("Dossier mis à jour : {} {}", updatedRecord.getFirstName(), updatedRecord.getLastName());
                return true;
            }
        }
        log.debug("Dossier non trouvé pour mise à jour : {} {}", updatedRecord.getFirstName(), updatedRecord.getLastName());
        return false;
    }

    public boolean deleteMedicalRecord(String firstName, String lastName) {
        Iterator<MedicalRecord> iterator = repository.getMedicalRecords().iterator();
        while (iterator.hasNext()) {
            MedicalRecord r = iterator.next();
            if (r.getFirstName().equals(firstName) && r.getLastName().equals(lastName)) {
                iterator.remove();
                log.debug("Dossier supprimé : {} {}", firstName, lastName);
                return true;
            }
        }
        log.debug("Dossier non trouvé pour suppression : {} {}", firstName, lastName);
        return false;
    }
}
