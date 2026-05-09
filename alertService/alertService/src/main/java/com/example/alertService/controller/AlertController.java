package com.example.alertService.controller;


import com.example.alertService.dto.AlertDto;
import com.example.alertService.service.IAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Alert REST APIs for SHEAS",
        description = "REST APIs to create and fetch alert history for patients"
)
@RestController
@RequestMapping(path = "/alert", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class AlertController {

    @Autowired
    private IAlertService iAlertService;


    @Operation(
            summary = "Create Alert",
            description = "Create a new alert for a patient when critical health data is detected"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    @PostMapping("/create")
    public ResponseEntity<AlertDto> createAlert(@RequestBody AlertDto alertDto){
        AlertDto createdAlert = iAlertService.createAlert(alertDto);
        log.info("Alert created for Patient ID: {}", alertDto.getPatientId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAlert);
    }


    @Operation(
            summary = "Get Alert History",
            description = "Fetch all alert history for a patient using patient ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Ok"
    )
    @GetMapping("/getAlertHistory")
    public ResponseEntity<List<AlertDto>> getAlertHistory(@RequestParam Long patientId){
        log.info("Fetching alert history for Patient ID: {}", patientId);
        List<AlertDto> alertDtos = iAlertService.getAlertHistory(patientId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(alertDtos);
    }
}
