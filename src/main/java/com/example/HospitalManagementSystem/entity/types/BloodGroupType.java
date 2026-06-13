package com.example.HospitalManagementSystem.entity.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING, with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public enum BloodGroupType {
    A_POSITIVE,
    A_NEGATIVE,
    B_POSITIVE,
    B_NEGATIVE,
    AB_POSITIVE,
    AB_NEGATIVE,
    O_POSITIVE,
    O_NEGATIVE;

    @JsonCreator
    public static BloodGroupType fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            // Converts "A_Positive" or "a_positive" to "A_POSITIVE" safely
            return BloodGroupType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown blood group: " + "error agya");
        }
    }

}
