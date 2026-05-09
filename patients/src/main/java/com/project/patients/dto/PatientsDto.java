package com.project.patients.dto;

import com.project.patients.entity.Patients;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
@Schema(
        name ="Patient",
        description = "Schema to hold patient details"
)
public class PatientsDto {

    @Schema(
            description = "Name of the Patient", example = "Akash"
    )
    @NotEmpty(message = "Name cannot be null")
    @Size(min = 3, max = 30, message = "Name Should be between 3 and 30")
    private String name;


    @Schema(
            description = "Age of the Patient"
    )
    @NotNull(message = "Age must be provided")
    @Min(value = 1, message = "Age must be greater than zero")

    private int age;

    @Schema(
            description = "Mobile Number of the Patient", example = "7894561230"
    )
    @NotEmpty(message = "Mobile Number cannot be null")
    @Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile number must be 10 digit")
    private String mobileNumber;


    private Long doctor_id;

    public PatientsDto() {

    }

    public PatientsDto(Patients patient) {
        this.name = patient.getName();
        this.age =patient.getAge();
        this.mobileNumber = patient.getMobileNumber();
        this.doctor_id =patient.getDoctor_id();
    }

    
}
