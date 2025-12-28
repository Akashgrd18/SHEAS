package com.example.alertService.dto;

import java.util.List;

public class AlertDto {
    private Long patientId;
    private List<String> alertMessage;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public List<String> getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(List<String> alertMessage) {
        this.alertMessage = alertMessage;
    }
}
