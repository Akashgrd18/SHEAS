package com.project.patients.controller;

import com.project.patients.dto.HealthDataDto;
import com.project.patients.dto.PatientDetailsWithAlertAndDoctor;

import com.project.patients.service.IHealthDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Health Data",
        description = "CRUD REST APIs for creation of Health data"
)
@RestController
@RequestMapping("/health")
@Slf4j
public class HealthDataController {

    private static final Logger log = LoggerFactory.getLogger(HealthDataController.class);
    @Autowired
    private IHealthDataService iHealthDataService;

    @Operation(
            summary = "Create healthdata",
            description = "Create HealthData manually and it will trigger if its critical"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Ok"
    )
    @PostMapping("/data")
    public ResponseEntity<PatientDetailsWithAlertAndDoctor> createData(@RequestBody HealthDataDto healthDataDto){
        iHealthDataService.createData(healthDataDto);
        PatientDetailsWithAlertAndDoctor patientDetailsWithAlertAndDoctor = iHealthDataService.checkData(healthDataDto);


        if(patientDetailsWithAlertAndDoctor.isStatus()) {
            log.info("Alert is created for for patient id: {} " , healthDataDto.getPatientId());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(patientDetailsWithAlertAndDoctor);
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(null);
        }

    }
}
