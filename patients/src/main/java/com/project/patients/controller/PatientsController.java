package com.project.patients.controller;


import com.project.patients.constants.PatientsConstants;
import com.project.patients.dto.ErrorResponseDto;
import com.project.patients.dto.PatientDetailsDto;
import com.project.patients.dto.PatientsDto;
import com.project.patients.dto.ResponseDto;
import com.project.patients.entity.Patients;
import com.project.patients.service.IPatientDetailsService;
import com.project.patients.service.IPatientsService;
import com.project.patients.service.impl.PatientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Patients in SHEAS",
        description = "CRUD REST APIs for Patients in SHEAS for Create, Update , Get and delete patients"
)

@RestController
@RequestMapping(path = "/patient", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Slf4j
public class PatientsController {


    private static final Logger log = LoggerFactory.getLogger(PatientsController.class);
    @Autowired
    private IPatientsService iPatientsService;

    @Autowired
    private IPatientDetailsService patientDetailsService;


    @Operation(
            summary = "Create Patients",
            description = "To register new patients"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPatient(@Valid @RequestBody PatientsDto patientsDto){


        iPatientsService.createPatient(patientsDto);
        log.info("Created Patient with Mobile Number :{}", patientsDto.getMobileNumber() );
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(PatientsConstants.STATUS_201, PatientsConstants.MESSAGE_201));
    }
    @Operation(
            summary = "Fetch Patients ",
            description = "Fetch Patients using mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Ok"
    )
    @GetMapping("/fetch")
    public ResponseEntity<PatientsDto> fetchByMobileNumber(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile number must be 10 digit") String mobileNumber){

        PatientsDto patientsDto = iPatientsService.fetchPatient(mobileNumber);


        log.info("Fetching Patient with Mobile Number :{}", mobileNumber );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patientsDto);

    }


    @Operation(
            summary = "update Patients ",
            description = "Update Patients "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "407", description = "Failed Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomer(@Valid @RequestBody PatientsDto patientsDto){

        boolean isUpdated = iPatientsService.updateAccount(patientsDto);
        if(isUpdated) {
            log.info("Updating Patient with Mobile Number :{} is success" , patientsDto.getMobileNumber() );
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PatientsConstants.STATUS_200, PatientsConstants.MESSAGE_200));
        }else{
            log.warn("Updating Patient with Mobile Number :{} is fail" , patientsDto.getMobileNumber() );
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(PatientsConstants.STATUS_417, PatientsConstants.MESSAGE_417_UPDATE));
        }
    }



    @Operation(
            summary = "Fetch Patients ",
            description = "Fetch Patients using ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Ok"
    )
    @GetMapping("/fetchById")
    public ResponseEntity<PatientsDto> fetchById(@RequestParam Long id){

        PatientsDto patientsDto = iPatientsService.fetchById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patientsDto);

    }



    @Operation(
            summary = "Delete Patients ",
            description = "Delete Patients using mobile number"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "407", description = "Failed Request")
    })

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteByMobileNumber(@RequestParam

                                                               @Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile number must be 10 digit") String mobileNumber){
        boolean isDeleted = iPatientsService.deletePatient(mobileNumber);
        if(isDeleted){
            log.info("Deleting Patient with Mobile Number :{} is success" , mobileNumber );
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PatientsConstants.STATUS_200, PatientsConstants.MESSAGE_200));
        }
        else{
            log.error("Deleting Patient with Mobile Number :{} is failed" , mobileNumber );
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(PatientsConstants.STATUS_417, PatientsConstants.MESSAGE_417_DELETE));
        }
    }


    @Operation(
            summary = "Fetch Patients with doctors details ",
            description = "Fetch Patients using mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Ok"
    )
    @GetMapping("/fetchPatientDetails")
    public ResponseEntity<PatientDetailsDto> fetchPatientDetails(@RequestParam
                                                                     @Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile number must be 10 digit") String mobileNumber){
        PatientDetailsDto patientDetailsDto = patientDetailsService.fetchPatientDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patientDetailsDto);
    }

    @Operation(
            summary = "Fetch Patients for fetching doctor details ",
            description = "Fetch Patients using IDs it will return list of patients"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Returns patients details for a particular doctor"
    )
    @PostMapping("/by-ids")
    public List<PatientsDto> getPatientsById(@RequestBody List<Long> patientIds){

        List<PatientsDto> patientsDto = iPatientsService.getPatientsById(patientIds);

        return patientsDto;

    }



}
