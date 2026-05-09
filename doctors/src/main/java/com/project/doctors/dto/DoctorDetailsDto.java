package com.project.doctors.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "DoctorDetails",
        description = "Schema to hold doctor details along with patient information"
)
public class DoctorDetailsDto {

    @Schema(description = "Name of the Doctor")
    private String name;

    @Schema(description = "Gender of the Doctor")
    private String gender;

    @Schema(description = "Mobile Number of the Doctor")
    private String mobileNumber;

    @Schema(description = "Specialization of the Doctor")
    private String specialization;

    @Schema(description = "List of patients assigned to this doctor")
    private List<PatientsDto> patientsDto;

}
