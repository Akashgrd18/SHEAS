package com.project.doctors.controller;

import com.project.doctors.constants.DoctorsConstants;
import com.project.doctors.dto.DoctorDetailsDto;
import com.project.doctors.dto.DoctorsDto;
import com.project.doctors.dto.ResponseDto;
import com.project.doctors.service.IDoctorDetailsService;
import com.project.doctors.service.IDoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/doctor", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DoctorsController {

    @Autowired
    private IDoctorsService iDoctorsService;


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createDoctor(@RequestBody DoctorsDto doctorsDto){
        iDoctorsService.createDoctor(doctorsDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(DoctorsConstants.STATUS_201,DoctorsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<DoctorsDto> fetchDoctor(@RequestParam String mobileNumber){
        DoctorsDto doctorsDto = iDoctorsService.fetchDoctor(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorsDto);
    }
    @PutMapping("/updateDoctorWithPatientsIds/{doctorId}/{patientId}")
    public ResponseEntity<ResponseDto> updateDoctor(@PathVariable Long doctorId, @PathVariable Long patientId){
        iDoctorsService.updateDoctor(doctorId,patientId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(DoctorsConstants.STATUS_201,DoctorsConstants.MESSAGE_201));
    }

    @GetMapping("/fetchById")
    public ResponseEntity<DoctorsDto> fetchDoctorById(@RequestParam Long id){
        DoctorsDto doctorsDto = iDoctorsService.fetchDoctorById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorsDto);
    }
    @Autowired
    private IDoctorDetailsService iDoctorDetailsService;

    @GetMapping("fetchDoctorDetails")
    public ResponseEntity<DoctorDetailsDto> fetchDoctorDetails(@RequestParam String mobileNumber){
        DoctorDetailsDto doctorDetailsDto = iDoctorDetailsService.fetchDoctorDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorDetailsDto);
    }

}
