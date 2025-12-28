package com.project.patients.service;

import com.project.patients.dto.PatientDetailsDto;

public interface IPatientDetailsService {
    PatientDetailsDto fetchPatientDetails(String mobileNumber);
}
