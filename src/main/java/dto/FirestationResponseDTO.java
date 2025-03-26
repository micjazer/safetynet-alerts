package com.safetynet.alerts.dto;

import java.util.List;

public class FirestationResponseDTO {
    private List<PersonSummaryDTO> persons;
    private int adults;
    private int children;

    public FirestationResponseDTO(List<PersonSummaryDTO> persons, int adults, int children) {
        this.persons = persons;
        this.adults = adults;
        this.children = children;
    }

    // Getters and Setters
}
