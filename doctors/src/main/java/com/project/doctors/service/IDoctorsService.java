package com.project.doctors.service;

import com.project.doctors.dto.DoctorsDto;

public interface IDoctorsService {
    void createDoctor(DoctorsDto doctorsDto);


    DoctorsDto fetchDoctor(String mobileNumber);

    DoctorsDto fetchDoctorById(Long id);

    void updateDoctor(Long doctorId, Long patientId);
}
