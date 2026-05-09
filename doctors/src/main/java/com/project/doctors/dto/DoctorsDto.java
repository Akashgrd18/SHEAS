package com.project.doctors.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "Doctor",
        description = "Schema to hold doctor details"
)
public class DoctorsDto {

    @Schema(
            description = "Name of the Doctor", example = "Dr. Smith"
    )
    @NotEmpty(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @Schema(
            description = "Gender of the Doctor", example = "Male"
    )
    @NotEmpty(message = "Gender cannot be null")
    private String gender;

    @Schema(
            description = "Mobile Number of the Doctor", example = "9876543210"
    )
    @NotEmpty(message = "Mobile Number cannot be null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Specialization of the Doctor", example = "Cardiologist"
    )
    @NotEmpty(message = "Specialization cannot be null")
    private String specialization;

    private List<Long> patients_id;

}
