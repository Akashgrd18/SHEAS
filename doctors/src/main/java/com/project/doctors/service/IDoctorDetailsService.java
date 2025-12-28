package com.project.doctors.service;

import com.project.doctors.dto.DoctorDetailsDto;

public interface IDoctorDetailsService {
    DoctorDetailsDto fetchDoctorDetails(String mobileNumber);
}
