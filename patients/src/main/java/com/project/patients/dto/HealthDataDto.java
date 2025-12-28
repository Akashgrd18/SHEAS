package com.project.patients.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Health Data",
        description = "Schema to hold Health data"
)
public class HealthDataDto {


    private Long patientId;
    private int heartbeat;
    private int bp;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public int getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(int heartbeat) {
        this.heartbeat = heartbeat;
    }

    public int getBp() {
        return bp;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }
}
