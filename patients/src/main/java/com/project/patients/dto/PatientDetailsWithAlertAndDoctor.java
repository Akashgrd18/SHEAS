package com.project.patients.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Patient Details With Doctors And alerts",
        description = "Schema to hold patient details with its alerts along with associated doctors"
)
public class PatientDetailsWithAlertAndDoctor {
    private PatientsDto patientsDto;

    private DoctorsDto doctorsDto;

    private AlertDto alertDto;

    private boolean status = false;

    public PatientsDto getPatientsDto() {
        return patientsDto;
    }

    public void setPatientsDto(PatientsDto patientsDto) {
        this.patientsDto = patientsDto;
    }

    public DoctorsDto getDoctorsDto() {
        return doctorsDto;
    }

    public void setDoctorsDto(DoctorsDto doctorsDto) {
        this.doctorsDto = doctorsDto;
    }

    public AlertDto getAlertDto() {
        return alertDto;
    }

    public void setAlertDto(AlertDto alertDto) {
        this.alertDto = alertDto;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
