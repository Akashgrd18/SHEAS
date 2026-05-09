package com.project.doctors.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Patient",
        description = "Schema to hold patient details for doctor-patient relationship"
)
public class PatientsDto {

    @Schema(description = "Name of the Patient")
    private String name;

    @Schema(description = "Age of the Patient")
    private int age;

    @Schema(description = "Mobile Number of the Patient")
    private String mobileNumber;

}
