package com.safetynet.alerts.dto;

import java.util.List;

public class ChildAlertDTO {
    private List<ChildWithAgeDTO> children;
    private List<String> otherHouseholdMembers;

    public ChildAlertDTO(List<ChildWithAgeDTO> children, List<String> otherHouseholdMembers) {
        this.children = children;
        this.otherHouseholdMembers = otherHouseholdMembers;
    }

    public List<ChildWithAgeDTO> getChildren() {
        return children;
    }

    public List<String> getOtherHouseholdMembers() {
        return otherHouseholdMembers;
    }
}

