package com.safetynet.alerts.dto;

public class PersonSummaryDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public PersonSummaryDTO(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    // Getters and Setters
}
