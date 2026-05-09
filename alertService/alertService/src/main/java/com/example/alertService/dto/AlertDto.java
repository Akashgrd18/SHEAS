package com.example.alertService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "Alert",
        description = "Schema to hold alert details for a patient"
)
public class AlertDto {

    @Schema(description = "ID of the patient this alert belongs to")
    private Long patientId;

    @Schema(description = "List of alert messages triggered for the patient")
    private List<String> alertMessage;

}
