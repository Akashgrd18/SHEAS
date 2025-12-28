package com.project.patients.service.client;


import com.project.patients.dto.DoctorsDto;
import com.project.patients.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name ="doctors" , url = "http://localhost:8090")
public interface DoctorsFeignClient {

    @GetMapping("/doctor/fetchById")
    public ResponseEntity<DoctorsDto> fetchDoctorById(@RequestParam("id") Long id);

    @PutMapping("/doctor/updateDoctorWithPatientsIds/{doctorId}/{patientId}")
    public ResponseEntity<ResponseDto> updateDoctor(@PathVariable Long doctorId, @PathVariable Long patientId);
}
