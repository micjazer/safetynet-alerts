package com.safetynet.alerts.dto;

import java.util.List;

public class FireResponseDTO {
    private List<FireResidentDTO> residents;
    private String station;

    public FireResponseDTO(List<FireResidentDTO> residents, String station) {
        this.residents = residents;
        this.station = station;
    }

    public List<FireResidentDTO> getResidents() {
        return residents;
    }

    public String getStation() {
        return station;
    }
}
