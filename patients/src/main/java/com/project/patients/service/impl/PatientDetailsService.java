package com.project.patients.service.impl;

import com.project.patients.dto.DoctorsDto;
import com.project.patients.dto.PatientDetailsDto;
import com.project.patients.dto.PatientsDto;
import com.project.patients.entity.Patients;
import com.project.patients.exception.ResourceNotFoundException;
import com.project.patients.mapper.PatientsMapper;
import com.project.patients.repository.PatientsRepository;
import com.project.patients.service.IPatientDetailsService;
import com.project.patients.service.client.DoctorsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatientDetailsService implements IPatientDetailsService {

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private DoctorsFeignClient doctorsFeignClient;


    @Override
    public PatientDetailsDto fetchPatientDetails(String mobileNumber) {

        Patients patients = patientsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "mobileNumber", mobileNumber));

        PatientDetailsDto patientDetailsDto = PatientsMapper.mapToPatientDetailsDto(patients, new PatientDetailsDto());

        ResponseEntity<DoctorsDto> doctorsDtoResponseEntity = doctorsFeignClient.fetchDoctorById(patients.getDoctor_id() );
        patientDetailsDto.setDoctorsDto(doctorsDtoResponseEntity.getBody());

        return patientDetailsDto;
    }
}
