package com.project.doctors.dto;

import java.util.List;

public class DoctorDetailsDto {
    private String name;
    private String gender;
    private String mobileNumber;
    private String  specialization;

    private List<PatientsDto> patientsDto;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<PatientsDto> getPatientsDto() {
        return patientsDto;
    }

    public void setPatientsDto(List<PatientsDto> patientsDto) {
        this.patientsDto = patientsDto;
    }
}
