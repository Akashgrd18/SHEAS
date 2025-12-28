package com.project.doctors.mapper;

//import com.project.doctors.dto.DoctorDetailsDto;
import com.project.doctors.dto.DoctorDetailsDto;
import com.project.doctors.dto.DoctorsDto;
import com.project.doctors.entity.Doctors;

public class DoctorsMapper {

    public static DoctorsDto mapToDoctorsDto(Doctors doctors, DoctorsDto doctorsDto){
        doctorsDto.setName(doctors.getName());
        doctorsDto.setGender(doctors.getGender());
        doctorsDto.setMobileNumber(doctors.getMobileNumber());
        doctorsDto.setSpecialization(doctors.getSpecialization());
        doctorsDto.setPatients_id(doctors.getPatients_id());

        return doctorsDto;
    }

        public static DoctorDetailsDto mapToDoctorDetailsDto(Doctors doctors, DoctorDetailsDto doctorDetailsDto){
            doctorDetailsDto.setName(doctors.getName());
             doctorDetailsDto.setGender(doctors.getGender());
             doctorDetailsDto.setMobileNumber(doctors.getMobileNumber());
             doctorDetailsDto.setSpecialization(doctors.getSpecialization());

            return  doctorDetailsDto;



    }

    public static Doctors mapToDoctors(DoctorsDto doctorsDto, Doctors doctors){
        doctors.setName(doctorsDto.getName());
        doctors.setGender(doctorsDto.getGender());
        doctors.setMobileNumber(doctorsDto.getMobileNumber());
        doctors.setSpecialization(doctorsDto.getSpecialization());
        doctors.setPatients_ids(doctorsDto.getPatients_id());

        return doctors;

    }


}
