package com.project.doctors.controller;

import com.project.doctors.constants.DoctorsConstants;
import com.project.doctors.dto.DoctorDetailsDto;
import com.project.doctors.dto.DoctorsDto;
import com.project.doctors.dto.ErrorResponseDto;
import com.project.doctors.dto.ResponseDto;
import com.project.doctors.service.IDoctorDetailsService;
import com.project.doctors.service.IDoctorsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Doctors in SHEAS",
        description = "CRUD REST APIs for Doctors in SHEAS for Create, Update, Get and Delete doctors"
)
@RestController
@RequestMapping(path = "/doctor", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Slf4j
public class DoctorsController {

    @Autowired
    private IDoctorsService iDoctorsService;

    @Autowired
    private IDoctorDetailsService iDoctorDetailsService;


    @Operation(
            summary = "Create Doctor",
            description = "To register a new doctor"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createDoctor(@Valid @RequestBody DoctorsDto doctorsDto){
        iDoctorsService.createDoctor(doctorsDto);
        log.info("Created Doctor with Mobile Number: {}", doctorsDto.getMobileNumber());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(DoctorsConstants.STATUS_201, DoctorsConstants.MESSAGE_201));
    }


    @Operation(
            summary = "Fetch Doctor",
            description = "Fetch Doctor using mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Ok"
    )
    @GetMapping("/fetch")
    public ResponseEntity<DoctorsDto> fetchDoctor(@RequestParam String mobileNumber){
        log.info("Fetching Doctor with Mobile Number: {}", mobileNumber);
        DoctorsDto doctorsDto = iDoctorsService.fetchDoctor(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorsDto);
    }


    @Operation(
            summary = "Update Doctor with Patient IDs",
            description = "Assign a patient to a doctor"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "417", description = "Failed Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/updateDoctorWithPatientsIds/{doctorId}/{patientId}")
    public ResponseEntity<ResponseDto> updateDoctor(@PathVariable Long doctorId, @PathVariable Long patientId){
        iDoctorsService.updateDoctor(doctorId, patientId);
        log.info("Updated Doctor ID: {} with Patient ID: {}", doctorId, patientId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(DoctorsConstants.STATUS_200, DoctorsConstants.MESSAGE_200));
    }


    @Operation(
            summary = "Fetch Doctor",
            description = "Fetch Doctor using ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Ok"
    )
    @GetMapping("/fetchById")
    public ResponseEntity<DoctorsDto> fetchDoctorById(@RequestParam Long id){
        log.info("Fetching Doctor with ID: {}", id);
        DoctorsDto doctorsDto = iDoctorsService.fetchDoctorById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorsDto);
    }


    @Operation(
            summary = "Fetch Doctor with patient details",
            description = "Fetch Doctor details along with their patients using mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Ok"
    )
    @GetMapping("/fetchDoctorDetails")
    public ResponseEntity<DoctorDetailsDto> fetchDoctorDetails(@RequestParam String mobileNumber){
        log.info("Fetching Doctor Details with Mobile Number: {}", mobileNumber);
        DoctorDetailsDto doctorDetailsDto = iDoctorDetailsService.fetchDoctorDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorDetailsDto);
    }

}
