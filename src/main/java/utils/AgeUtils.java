package com.safetynet.alerts.utils;

import com.safetynet.alerts.model.MedicalRecord;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AgeUtils {
    public static int calculateAge(String firstName, String lastName, List<MedicalRecord> records) {
        return records.stream()
                .filter(mr -> mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName))
                .findFirst()
                .map(mr -> {
                    LocalDate birthdate = LocalDate.parse(mr.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    return Period.between(birthdate, LocalDate.now()).getYears();
                })
                .orElse(0);
    }
}
