package com.project.patients.service;

import com.project.patients.dto.PatientsDto;
import com.project.patients.entity.Patients;

import java.util.List;

public interface IPatientsService {

    public void createPatient(PatientsDto patientsDto);


    PatientsDto fetchPatient(String mobileNumber);

    boolean updateAccount(PatientsDto patientsDto);

    boolean deletePatient(String mobileNumber);

    PatientsDto fetchById(Long id);

    List<PatientsDto> getPatientsById(List<Long> patientIds);
}
