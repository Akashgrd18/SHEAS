package com.project.patients.service;

import com.project.patients.dto.HealthDataDto;
import com.project.patients.dto.PatientDetailsWithAlertAndDoctor;
import org.springframework.http.ResponseEntity;

public interface IHealthDataService {
    void createData(HealthDataDto healthDataDto);

    PatientDetailsWithAlertAndDoctor checkData(HealthDataDto healthDataDto);
}
