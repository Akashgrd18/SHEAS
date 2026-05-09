package com.project.doctors.service.impl;

import com.project.doctors.dto.DoctorsDto;
import com.project.doctors.entity.Doctors;
import com.project.doctors.exception.DoctorAlreadyExistsException;
import com.project.doctors.exception.ResourceNotFoundException;
import com.project.doctors.mapper.DoctorsMapper;
import com.project.doctors.repository.DoctorsRepository;
import com.project.doctors.service.IDoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class  DoctorsService implements IDoctorsService {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Override
    public void createDoctor(DoctorsDto doctorsDto) {
        Doctors doctors = DoctorsMapper.mapToDoctors(doctorsDto, new Doctors());
        Optional<Doctors> optionalDoctors =doctorsRepository.findByMobileNumber(doctorsDto.getMobileNumber());
        if(optionalDoctors.isPresent()){
            throw new DoctorAlreadyExistsException("Doctor already exists with this mobile number"+ doctorsDto.getMobileNumber());
        }

        doctorsRepository.save(doctors);
    }

    @Override
    public DoctorsDto fetchDoctor(String mobileNumber) {
        Doctors doctors = doctorsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Doctor", "mobileNumber", mobileNumber)
                );
        DoctorsDto doctorsDto = DoctorsMapper.mapToDoctorsDto(doctors, new DoctorsDto() );
        return doctorsDto;
    }

    @Override
    public DoctorsDto fetchDoctorById(Long id) {

        Doctors doctors = doctorsRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Doctor", "id", id.toString())
                );
        DoctorsDto doctorsDto = DoctorsMapper.mapToDoctorsDto(doctors, new DoctorsDto() );
        return doctorsDto;
    }

    @Override

    public void updateDoctor(Long doctorId, Long patientId) {
        Doctors doctors = doctorsRepository.findById(doctorId).orElseThrow(
                ()-> new ResourceNotFoundException("Doctor", "id", doctorId.toString())
        );

        List<Long> patientList = doctors.getPatients_id();
        if (patientList == null) {
            patientList = new ArrayList<>();
        }
        patientList.add(patientId);
        doctors.setPatients_id(patientList);
        doctorsRepository.save(doctors);

    }
}
