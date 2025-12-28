package com.project.doctors.service.client;

import com.project.doctors.dto.PatientsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("patients")
public interface PatientsFeignClient {
    @PostMapping(value = "/patient/by-ids", consumes = "application/json")
    public List<PatientsDto> getPatientsById(@RequestBody List<Long> patientIds);
}
