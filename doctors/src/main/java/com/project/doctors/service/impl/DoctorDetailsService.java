package com.project.doctors.service.impl;


import com.project.doctors.dto.DoctorDetailsDto;
import com.project.doctors.dto.PatientsDto;
import com.project.doctors.entity.Doctors;
import com.project.doctors.exception.ResourceNotFoundException;
import com.project.doctors.mapper.DoctorsMapper;
import com.project.doctors.repository.DoctorsRepository;
import com.project.doctors.service.IDoctorDetailsService;
import com.project.doctors.service.client.PatientsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorDetailsService implements IDoctorDetailsService {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private PatientsFeignClient patientsFeignClient;

    @Override
    public DoctorDetailsDto fetchDoctorDetails(String mobileNumber) {
        Doctors doctors = doctorsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("doctor", "mobileNumber", mobileNumber)
                );
        DoctorDetailsDto doctorDetailsDto = DoctorsMapper.mapToDoctorDetailsDto(doctors, new DoctorDetailsDto());

        List<PatientsDto> patientsDtoResponse = patientsFeignClient.getPatientsById(doctors.getPatients_id());
        doctorDetailsDto.setPatientsDto(patientsDtoResponse);

        return doctorDetailsDto;
    }
}
