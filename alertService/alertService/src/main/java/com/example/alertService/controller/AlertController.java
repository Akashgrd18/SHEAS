package com.example.alertService.controller;


import com.example.alertService.dto.AlertDto;
import com.example.alertService.service.IAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlertController {

    @Autowired
    private IAlertService iAlertService;


    @PostMapping("/create")
    public AlertDto createAlert(@RequestBody AlertDto alertDto){
        iAlertService.createAlert(alertDto);

        return alertDto;
    }

    @GetMapping("/getAlertHistory")
    public ResponseEntity<List<AlertDto>> getAlertHistory(@RequestParam Long patientId){
        List<AlertDto> alertDtos =  iAlertService.getAlertHistory(patientId);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(alertDtos);

    }
}
