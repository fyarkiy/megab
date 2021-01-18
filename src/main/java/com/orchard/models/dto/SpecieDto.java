package com.orchard.models.dto;

public class SpecieDto {
    private String specieName;

    public SpecieDto(String specieName) {
        this.specieName = specieName;
    }

    public SpecieDto() {
    }

    public String getSpecieName() {
        return specieName;
    }

    public void setSpecieName(String specieName) {
        this.specieName = specieName;
    }
}
