package com.project.patients.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(
        name = "Alerts",
        description = "Schema to hold alert messages"
)
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
