package com.example.alertService.mapper;

import com.example.alertService.dto.AlertDto;
import com.example.alertService.entity.Alert;

public class AlertMapper {
    public static Alert mapToAlert(AlertDto alertDto, Alert alert){
        alert.setPatientId(alertDto.getPatientId());
        alert.setAlertMessage(alertDto.getAlertMessage());

        return  alert;
    }

    public static AlertDto mapToAlertDto(Alert alert, AlertDto alertDto){
        alertDto.setPatientId(alert.getPatientId());
        alertDto.setAlertMessage(alert.getAlertMessage());

        return  alertDto;
    }
}
